package com.siwz.app.api.interfaces;

import com.siwz.app.api.forms.employee.EmployeeCreateRequest;
import com.siwz.app.api.forms.ErrorForm;
import com.siwz.app.api.forms.Form;
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

@RequestMapping(value = "/employees")
@Tag(name = "employees", description = "The employees API")
public interface EmployeeApi {

    @Operation(summary = "Find employee", description = "Find employee by Id", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = EmployeeGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(schema = @Schema(implementation = ErrorForm.class)))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<? extends Form> getEmployee(@PathVariable("id") Long id);

    @Operation(summary = "Create employee", description = "Create new employee", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = EmployeeCreateResponse.class)))
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<? extends Form> createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest);

    @Operation(summary = "Update employee", description = "Update employee data by Id", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(schema = @Schema(implementation = ErrorForm.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<? extends Form> updateEmployee(@Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest, @PathVariable("id") Long id);

    @Operation(summary = "Delete employee", description = "Delete employee by Id", tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(schema = @Schema(implementation = ErrorForm.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<? extends Form> deleteEmployee(@PathVariable("id") Long id);
}
