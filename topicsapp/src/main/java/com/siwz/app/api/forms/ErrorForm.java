package com.siwz.app.api.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorForm implements Form {

    @Schema(description = "HTTP status error code",
            example = "404")
    private String errorCode;

    @Schema(description = "Error description and details",
            example = "Entity with given id does not exist")
    private String errorDescription;
}
