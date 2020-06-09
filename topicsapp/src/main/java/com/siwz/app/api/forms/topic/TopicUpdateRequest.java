package com.siwz.app.api.forms.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class TopicUpdateRequest {

    @Schema(description = "Topic name",
            example = "Aplikacja do rezerwowania tematów projektowych")
    @JsonProperty
    private String name;

    @Schema(description = "Topic description",
            example = "Pełen opis wymagań")
    @JsonProperty
    private String description;

    @Schema(description = "Topic students limit",
            example = "4")
    @JsonProperty
    private Long limit;

    @Schema(description = "Topic deadline date",
            example = "30/06/2020")
    @JsonProperty
    private Date deadlineDate;
}
