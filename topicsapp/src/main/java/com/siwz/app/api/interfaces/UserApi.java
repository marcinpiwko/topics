package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.ErrorResponse;
import com.siwz.app.api.forms.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.topic.TopicGetResponse;
import com.siwz.app.api.forms.topic.TopicsGetResponse;
import com.siwz.app.api.forms.user.UserCreateRequest;
import com.siwz.app.api.forms.user.UserGetResponse;
import com.siwz.app.api.forms.user.UserUpdateRequest;
import com.siwz.app.api.forms.user.UsersByRoleGetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/users")
@Tag(name = "users", description = "The user API")
public interface UserApi {

    @Operation(summary = "Create user", description = "Create new user", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = IdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<? extends ResponseForm> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest);

    @Operation(summary = "Update user", description = "Update user data by Id", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping(value = "/{id}", consumes = "application/json")
    ResponseEntity<? extends ResponseForm> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest);

    @Operation(summary = "Delete user", description = "Delete user data by Id", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<? extends ResponseForm> deleteUser(@PathVariable("id") Long id);

    @Operation(summary = "Get users by role", description = "Get list of students/teachers", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = UsersByRoleGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(produces = "application/json")
    ResponseEntity<? extends ResponseForm> getUsersByRole(@RequestParam("role") String role);

    @Operation(summary = "Get user", description = "Get user details by Id", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = UserGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<? extends ResponseForm> getUser(@PathVariable("id") Long id);
}
