package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.common.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.user.UserCreateRequest;
import com.siwz.app.api.forms.user.UserUpdateRequest;
import com.siwz.app.api.forms.user.UsersByRoleGetResponse;
import com.siwz.app.api.interfaces.UserApi;
import com.siwz.app.api.translators.UserTranslator;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController implements UserApi {

    private final UserService userService;

    private final UserTranslator userTranslator;

    @Override
    public ResponseEntity<? extends ResponseForm> createUser(@Valid UserCreateRequest userCreateRequest) {
        try {
            Long userId = userService.createUser(userTranslator.translateToService(userCreateRequest));
            return ApiResponse.ok(new IdResponse(userId));
        } catch(ApplicationException e) {
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> updateUser(Long id, @Valid UserUpdateRequest userUpdateRequest) {
        try {
            userService.updateUser(id, userTranslator.translateToService(userUpdateRequest));
            return ApiResponse.noContent();
        } catch(ApplicationException e) {
            if(DAOError.DAO_USER_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> deleteUser(Long id) {
        try {
            userService.deleteUser(id);
            return ApiResponse.noContent();
        } catch(ApplicationException e) {
            if(DAOError.DAO_USER_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> getUsersByRole(String role) {
        try {
            return ApiResponse.ok(new UsersByRoleGetResponse(userService.getUsersByRole(role)
                    .stream()
                    .map(userTranslator::translateToUserGetResponse)
                    .collect(Collectors.toList())));
        } catch(ApplicationException e) {
            if(DAOError.DAO_ROLE_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> getUser(Long id) {
        try {
            return ApiResponse.ok(userTranslator.translateToUserGetResponse(userService.getUserById(id)));
        } catch(ApplicationException e) {
            if(DAOError.DAO_USER_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }
}
