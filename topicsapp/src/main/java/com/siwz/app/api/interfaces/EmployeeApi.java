package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.employee.EmployeeCreateRequest;
import com.siwz.app.api.forms.ErrorResponse;
import com.siwz.app.api.forms.employee.EmployeeCreateResponse;
import com.siwz.app.api.forms.employee.EmployeeGetResponse;
import com.siwz.app.api.forms.employee.EmployeeUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/employees")
@Tag(name = "employees", description = "The employees API")
public interface EmployeeApi {

    @Operation(summary = "Find employee", description = "Find employee by Id", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = EmployeeGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<? extends ResponseForm> getEmployee(@PathVariable("id") Long id);

    @Operation(summary = "Create employee", description = "Create new employee", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = EmployeeCreateResponse.class)))
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<? extends ResponseForm> createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest);

    @Operation(summary = "Update employee", description = "Update employee data by Id", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<? extends ResponseForm> updateEmployee(@Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest, @PathVariable("id") Long id);

    @Operation(summary = "Delete employee", description = "Delete employee by Id", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<? extends ResponseForm> deleteEmployee(@PathVariable("id") Long id);
}
