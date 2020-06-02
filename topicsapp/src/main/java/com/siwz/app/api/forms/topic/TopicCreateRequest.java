package com.siwz.app.api.forms.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TopicCreateRequest {

    @Schema(description = "Topic name",
            example = "Aplikacja do rezerwowania tematów projektowych")
    @JsonProperty
    @NotNull
    private String name;

    @Schema(description = "Topic description",
            example = "Pełen opis funkcjonalności")
    @JsonProperty
    @NotNull
    private String description;

    @Schema(description = "Topic students limit",
            example = "4")
    @JsonProperty
    @NotNull
    private Long limit;

    @Schema(description = "Topic deadline date",
            example = "30/06/2020")
    @JsonProperty
    @NotNull
    private Date deadlineDate;
}
