package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.login.LoginRequest;
import com.siwz.app.api.forms.login.LoginResponse;
import com.siwz.app.api.interfaces.AuthApi;
import com.siwz.app.services.interfaces.AuthService;
import com.siwz.app.utils.errors.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<? extends ResponseForm> login(LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(authService.getUserToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return ApiResponse.ok(loginResponse);
        } catch (ApplicationException e) {
            return ApiResponse.badRequest(e.getMessage());
        }
    }
}
