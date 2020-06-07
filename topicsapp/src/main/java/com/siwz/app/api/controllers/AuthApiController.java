package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.login.LoginRequest;
import com.siwz.app.api.forms.login.LoginResponse;
import com.siwz.app.api.interfaces.AuthApi;
import com.siwz.app.api.security.JwtToken;
import com.siwz.app.persistence.dto.User;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthApiController implements AuthApi {

    private final AuthenticationManager authenticationManager;

    private final JwtToken jwtToken;

    private final UserService userService;

    @Override
    public ResponseEntity<? extends ResponseForm> login(LoginRequest loginRequest) {
        try {
            authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        } catch (ApplicationException e) {
            return ApiResponse.unauthorized(e.getMessage());
        }
        User userDetails = (User) userService.loadUserByUsername(loginRequest.getEmail());
        return ApiResponse.ok(new LoginResponse(userDetails.getId(), jwtToken.generateToken(userDetails)));
    }

    private void authenticate(String username, String password) throws ApplicationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new ApplicationException(DAOError.DAO_INVALID_CREDENTIALS);
        }
    }
}
