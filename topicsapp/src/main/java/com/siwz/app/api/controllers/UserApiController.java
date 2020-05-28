package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.user.UserCreateRequest;
import com.siwz.app.api.interfaces.UserApi;
import com.siwz.app.api.translators.UserTranslator;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.errors.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController implements UserApi {

    private final UserService userService;

    private final UserTranslator userTranslator;

    @Override
    public ResponseEntity<? extends ResponseForm> createUser(@Valid UserCreateRequest userCreateRequest) {
        try {
            userService.createUser(userTranslator.translateToService(userCreateRequest));
            return ApiResponse.noContent();
        } catch(ApplicationException e) {
            return ApiResponse.badRequest(e.getMessage());
        }
    }
}
