package com.siwz.app.api.forms.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubjectsGetResponse implements ResponseForm {

    @Schema(description = "List of subjects")
    @JsonProperty
    private List<SubjectGetResponse> subjects;
}
