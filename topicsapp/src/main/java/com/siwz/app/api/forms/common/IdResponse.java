package com.siwz.app.api.forms.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siwz.app.api.forms.ResponseForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class IdResponse implements ResponseForm {

    @Schema(description = "Entity id",
            example = "1")
    @JsonProperty
    @NotNull
    private Long id;
}
