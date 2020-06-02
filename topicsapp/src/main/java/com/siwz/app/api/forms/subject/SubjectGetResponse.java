package com.siwz.app.api.forms.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.user.UserGetResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectGetResponse implements ResponseForm {

    @Schema(description = "Subject unique Id",
            example = "1")
    @JsonProperty
    @NotNull
    private Long id;

    @Schema(description = "Subject name",
            example = "Systemy operacyjne")
    @JsonProperty
    @NotNull
    private String name;

    @Schema(description = "Subject description",
            example = "Szczegółowy opis programu nauczania")
    @JsonProperty
    @NotNull
    private String description;

    @Schema(description = "Subject teacher")
    @JsonProperty
    @NotNull
    private UserGetResponse teacher;

    @Schema(description = "Subject ects points",
            example = "5")
    @JsonProperty
    @NotNull
    private Integer ects;
}
