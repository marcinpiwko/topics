package com.siwz.app.api.forms.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectCreateRequest {

    @Schema(description = "Subject name",
            example = "Systemy operacyjne")
    @JsonProperty
    @NotNull
    private String name;

    @Schema(description = "User password",
            example = "Na tym przedmiocie na pewno uwalisz")
    @JsonProperty
    @NotNull
    private String description;

    @Schema(description = "Teacher Id",
            example = "1")
    @JsonProperty
    @NotNull
    private Long userId;

    @Schema(description = "Number of ects points",
            example = "5")
    @JsonProperty
    @NotNull
    private Integer ects;
}
