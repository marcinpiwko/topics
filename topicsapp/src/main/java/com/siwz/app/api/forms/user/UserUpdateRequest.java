package com.siwz.app.api.forms.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @Schema(description = "Login (email)",
            example = "marcinpiwko97@gmail.com")
    @JsonProperty
    private String email;

    @Schema(description = "User first name",
            example = "Marcin")
    @JsonProperty
    private String firstName;

    @Schema(description = "User last name",
            example = "Piwko")
    @JsonProperty
    private String lastName;

    @Schema(description = "Student identity number",
            example = "123123")
    @JsonProperty
    private String indexNo;
}
