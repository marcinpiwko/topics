package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.employee.EmployeeCreateRequest;
import com.siwz.app.api.forms.Form;
import com.siwz.app.api.forms.employee.EmployeeUpdateRequest;
import com.siwz.app.api.interfaces.EmployeeApi;
import com.siwz.app.api.translators.EmployeeTranslator;
import com.siwz.app.persistence.dto.Employee;
import com.siwz.app.services.interfaces.EmployeeService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class EmployeeApiController implements EmployeeApi {

    private final EmployeeService employeeService;

    private final EmployeeTranslator employeeTranslator;

    private static String domain = "Employee";

    @Override
    public ResponseEntity<? extends Form> getEmployee(Long id) {
        try {
            Employee employee = employeeService.getEmployee(id);
            return ApiResponse.ok(employeeTranslator.translateToApiGetResponse(employee));
        } catch (ApplicationException e) {
            if(DAOError.DAO_ITEM_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(id, domain);
            }
            return ApiResponse.badRequest();
        }
    }

    @Override
    public ResponseEntity<? extends Form> createEmployee(EmployeeCreateRequest employeeCreateRequest) {
        Long id = employeeService.createEmployee(employeeTranslator.translateToService(employeeCreateRequest));
        return ApiResponse.ok(employeeTranslator.translateToApiCreateResponse(id));
    }

    @Override
    public ResponseEntity<? extends Form> updateEmployee(EmployeeUpdateRequest employeeUpdateRequest, Long id) {
        try {
            employeeService.updateEmployee(id, employeeTranslator.translateToService(employeeUpdateRequest));
        } catch (ApplicationException e) {
            if(DAOError.DAO_ITEM_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(id, domain);
            }
            return ApiResponse.badRequest();
        }
        return ApiResponse.noContent();
    }

    @Override
    public ResponseEntity<? extends Form> deleteEmployee(Long id) {
        try {
            employeeService.deleteEmployee(id);
        } catch (ApplicationException e) {
            if(DAOError.DAO_ITEM_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(id, domain);
            }
            return ApiResponse.badRequest();
        }
        return ApiResponse.noContent();
    }
}
