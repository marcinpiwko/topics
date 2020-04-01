package com.siwz.app.api.controllers;

import com.siwz.app.api.forms.EmployeeForm;
import com.siwz.app.api.translators.EmployeeTranslator;
import com.siwz.app.persistence.dto.Employee;
import com.siwz.app.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeApiController {

    private final EmployeeService employeeService;

    private final EmployeeTranslator employeeTranslator;

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeForm> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeTranslator.translateToApi(employee), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody EmployeeForm employeeForm) {
        employeeService.createEmployee(employeeTranslator.translateToService(employeeForm));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
