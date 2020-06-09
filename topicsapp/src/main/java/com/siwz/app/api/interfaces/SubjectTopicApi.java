package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.common.ErrorResponse;
import com.siwz.app.api.forms.common.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.topic.TopicCreateRequest;
import com.siwz.app.api.forms.topic.TopicGetResponse;
import com.siwz.app.api.forms.topic.TopicUpdateRequest;
import com.siwz.app.api.forms.topic.TopicsGetResponse;
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
@Tag(name = "subject-topics", description = "The subject-topics API")
public interface SubjectTopicApi {

    @Operation(summary = "Create subject topic", description = "Create new subject topic", tags = "subject-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = IdResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subject not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/{subjectId}/topics", consumes = "application/json", produces = "application/json")
    ResponseEntity<? extends ResponseForm> createSubjectTopic(@PathVariable("subjectId") Long subjectId, @Valid @RequestBody TopicCreateRequest topicCreateRequest);

    @Operation(summary = "Update subject topic", description = "Update subject topic", tags = "subject-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Subject or topic not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping(value = "/{subjectId}/topics/{topicId}", consumes = "application/json")
    ResponseEntity<? extends ResponseForm> updateSubjectTopic(@PathVariable("subjectId") Long subjectId, @PathVariable("topicId") Long topicId, @Valid @RequestBody TopicUpdateRequest topicUpdateRequest);

    @Operation(summary = "Delete subject topic", description = "Delete subject topic", tags = "subject-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Subject or topic not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping(value = "/{subjectId}/topics/{topicId}", consumes = "application/json")
    ResponseEntity<? extends ResponseForm> deleteSubjectTopic(@PathVariable("subjectId") Long subjectId, @PathVariable("topicId") Long topicId);

    @Operation(summary = "Get subject topic", description = "Get subject topic details", tags = "subject-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = TopicGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subject or topic not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{subjectId}/topics/{topicId}", produces = "application/json")
    ResponseEntity<? extends ResponseForm> getSubjectTopic(@PathVariable("subjectId") Long subjectId, @PathVariable("topicId") Long topicId);

    @Operation(summary = "Get subject topics", description = "Get list of subject topics", tags = "subject-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = TopicsGetResponse.class)))
    })
    @GetMapping(value = "/{subjectId}/topics", produces = "application/json")
    ResponseEntity<? extends ResponseForm> getSubjectTopics(@PathVariable("subjectId") Long subjectId);
}
