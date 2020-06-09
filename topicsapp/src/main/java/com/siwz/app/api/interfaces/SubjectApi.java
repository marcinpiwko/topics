package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.common.ErrorResponse;
import com.siwz.app.api.forms.common.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.subject.SubjectCreateRequest;
import com.siwz.app.api.forms.subject.SubjectGetResponse;
import com.siwz.app.api.forms.subject.SubjectUpdateRequest;
import com.siwz.app.api.forms.subject.SubjectsGetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Update subject", description = "Update subject", tags = "subjects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping(value = "/{id}", consumes = "application/json")
    ResponseEntity<? extends ResponseForm> updateSubject(@PathVariable("id") Long id, @Valid @RequestBody SubjectUpdateRequest subjectUpdateRequest);

    @Operation(summary = "Delete subject", description = "Delete subject", tags = "subjects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<? extends ResponseForm> deleteSubject(@PathVariable("id") Long id);

    @Operation(summary = "Get subject", description = "Get subject details", tags = "subjects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = SubjectGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<? extends ResponseForm> getSubject(@PathVariable("id") Long id);

    @Operation(summary = "Get all subjects", description = "Get list of subjects", tags = "subjects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = SubjectsGetResponse.class)))
    })
    @GetMapping(produces = "application/json")
    ResponseEntity<? extends ResponseForm> getSubjects();
}
