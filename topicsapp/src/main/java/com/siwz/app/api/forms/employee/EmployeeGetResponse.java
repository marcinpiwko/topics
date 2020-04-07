package com.siwz.app.api.forms.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.Form;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeGetResponse implements Form {

    @Schema(description = "Employee first name",
            example = "John")
    @JsonProperty
    @NotNull
    private String firstName;

    @Schema(description = "Employee last name",
            example = "Snow")
    @JsonProperty
    @NotNull
    private String lastName;
}
