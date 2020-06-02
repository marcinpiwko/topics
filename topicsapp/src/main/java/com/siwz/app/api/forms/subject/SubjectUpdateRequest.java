package com.siwz.app.api.forms.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SubjectUpdateRequest {

    @Schema(description = "Subject name",
            example = "Systemy operacyjne")
    @JsonProperty
    private String name;

    @Schema(description = "User password",
            example = "Na tym przedmiocie na pewno uwalisz")
    @JsonProperty
    private String description;

    @Schema(description = "Number of ects points",
            example = "5")
    @JsonProperty
    private Integer ects;
}
