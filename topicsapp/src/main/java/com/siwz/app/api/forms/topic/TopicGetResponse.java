package com.siwz.app.api.forms.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.subject.SubjectGetResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TopicGetResponse implements ResponseForm {

    @Schema(description = "Topic unique Id",
            example = "1")
    @JsonProperty
    @NotNull
    private Long id;

    @Schema(description = "Topic name",
            example = "Aplikacja do rezerwowania tematów projektowych")
    @JsonProperty
    @NotNull
    private String name;

    @Schema(description = "Topic description",
            example = "Pełen opis wymagań")
    @JsonProperty
    @NotNull
    private String description;

    @Schema(description = "Topic associated subject",
            example = "Systemy informatyczne w zarządzaniu")
    @JsonProperty
    @NotNull
    private SubjectGetResponse subject;

    @Schema(description = "Topic students limit",
            example = "4")
    @JsonProperty
    @NotNull
    private Long limit;

    @Schema(description = "Topic creation date",
            example = "01/06/2020")
    @JsonProperty
    @NotNull
    private Date creationDate;

    @Schema(description = "Topic deadline date",
            example = "30/06/2020")
    @JsonProperty
    @NotNull
    private Date deadlineDate;
}
