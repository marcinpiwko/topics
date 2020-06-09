package com.siwz.app.api.forms.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @Schema(description = "Login (email)",
            example = "marcinpiwko97@gmail.com")
    @JsonProperty
    @NotNull
    private String email;

    @Schema(description = "User password",
            example = "string")
    @JsonProperty
    @NotNull
    private String password;
}
