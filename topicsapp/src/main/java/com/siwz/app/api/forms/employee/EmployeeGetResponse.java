package com.siwz.app.api.forms.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeGetResponse implements ResponseForm {

    @Schema(description = "Employee first type",
            example = "John")
    @JsonProperty
    @NotNull
    private String firstName;

    @Schema(description = "Employee last type",
            example = "Snow")
    @JsonProperty
    @NotNull
    private String lastName;
}
