package com.siwz.app.api.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements ResponseForm {

    @Schema(description = "HTTP status error code",
            example = "404 NOT_FOUND")
    private String errorCode;

    @Schema(description = "Error description and details",
            example = "Not found entity with given id")
    private String errorDescription;
}
