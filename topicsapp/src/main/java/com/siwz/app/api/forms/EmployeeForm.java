package com.siwz.app.api.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmployeeForm {

    @JsonProperty
    @NotNull
    private String firstName;

    @JsonProperty
    @NotNull
    private String lastName;
}
