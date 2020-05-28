package com.siwz.app.api.forms.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateRequest {

    @Schema(description = "Login (email)",
            example = "marcinpiwko97@gmail.com")
    @JsonProperty
    @NotNull
    private String email;

    @Schema(description = "User password")
    @JsonProperty
    @NotNull
    private String password;

    @Schema(description = "User first type")
    @JsonProperty
    @NotNull
    private String firstName;

    @Schema(description = "User last type")
    @JsonProperty
    @NotNull
    private String lastName;

    @Schema(description = "STUDENT identity number")
    @JsonProperty
    private String indexNo;
}
