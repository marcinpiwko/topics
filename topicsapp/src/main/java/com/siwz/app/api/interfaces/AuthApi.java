package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.ErrorResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.login.LoginRequest;
import com.siwz.app.api.forms.login.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/login")
@Tag(name = "authorization", description = "The authorization API")
public interface AuthApi {

    @Operation(summary = "Login", description = "User login", tags = "authorization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<? extends ResponseForm> login(@Valid @RequestBody LoginRequest loginRequest);
}
