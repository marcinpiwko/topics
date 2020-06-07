package com.siwz.app.api.forms.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginResponse implements ResponseForm {

    @Schema(description = "User id")
    @JsonProperty
    @NotNull
    private Long userId;

    @Schema(description = "JSON Web Token")
    @JsonProperty
    @NotNull
    private String token;

    public LoginResponse(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
