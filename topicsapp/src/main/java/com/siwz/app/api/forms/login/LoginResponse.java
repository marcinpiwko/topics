package com.siwz.app.api.forms.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginResponse implements ResponseForm {

    @Schema(description = "User id",
            example = "1")
    @JsonProperty
    @NotNull
    private Long userId;

    @Schema(description = "JSON Web Token - access token to API (use in Authorization header)",
            example = "adufg4i7iqbiae8f7d90h8347adfiha8d76fgsad76fr3hfskasdfg")
    @JsonProperty
    @NotNull
    private String token;

    public LoginResponse(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
