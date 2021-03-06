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

    @Schema(description = "User password",
            example = "password")
    @JsonProperty
    @NotNull
    private String password;

    @Schema(description = "User first name",
            example = "Marcin")
    @JsonProperty
    @NotNull
    private String firstName;

    @Schema(description = "User last name",
            example = "Piwko")
    @JsonProperty
    @NotNull
    private String lastName;

    @Schema(description = "Student identity number",
            example = "123123")
    @JsonProperty
    private String indexNo;
}
