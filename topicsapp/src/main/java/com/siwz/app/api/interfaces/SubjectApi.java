package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.ErrorResponse;
import com.siwz.app.api.forms.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.subject.SubjectCreateRequest;
import com.siwz.app.api.forms.user.UserCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/subjects")
@Tag(name = "subjects", description = "The subject API")
public interface SubjectApi {

    @Operation(summary = "Create subject", description = "Create new subject", tags = "subjects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = IdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<? extends ResponseForm> createSubject(@Valid @RequestBody SubjectCreateRequest subjectCreateRequest);
}
