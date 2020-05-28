package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.user.UserCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/users")
@Tag(name = "users", description = "The user API")
public interface UserApi {

    @Operation(summary = "Create user", description = "Create new user", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<? extends ResponseForm> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest);
}
