package com.siwz.app.api.forms.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersByRoleGetResponse implements ResponseForm {

    @Schema(description = "List of all students/teachers")
    @JsonProperty
    private List<UserGetResponse> users;
}
