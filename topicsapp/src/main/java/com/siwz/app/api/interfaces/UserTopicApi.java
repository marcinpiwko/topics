package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.ErrorResponse;
import com.siwz.app.api.forms.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.topic.TopicGetResponse;
import com.siwz.app.api.forms.topic.TopicsGetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/users")
@Tag(name = "user-topics", description = "The user-topics API")
public interface UserTopicApi {

    @Operation(summary = "Get user assigned topic", description = "Get user assigned topic by subject", tags = "user-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = TopicGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "User or subject not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{userId}/subjects/{subjectId}/topic", produces = "application/json")
    ResponseEntity<? extends ResponseForm> getUserAssignedTopic(@PathVariable("userId") Long userId, @PathVariable("subjectId") Long subjectId);

    @Operation(summary = "Get all user assigned topics", description = "Get all user assigned topic from any subject", tags = "user-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = TopicsGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{userId}/topics", produces = "application/json")
    ResponseEntity<? extends ResponseForm> getAllUserAssignedTopics(@PathVariable("userId") Long userId);

    @Operation(summary = "Reserve topic", description = "Reserve topic by subject and topic Id", tags = "user-topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = IdResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subject or topic not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/{userId}/subjects/{subjectId}/topics/{topicId}", produces = "application/json")
    ResponseEntity<? extends ResponseForm> reserveTopic(@PathVariable("userId") Long userId, @PathVariable("subjectId") Long subjectId, @PathVariable("topicId") Long topicId);
}
