package com.siwz.app.api.translators;

import com.siwz.app.api.forms.employee.EmployeeCreateRequest;
import com.siwz.app.api.forms.employee.EmployeeCreateResponse;
import com.siwz.app.api.forms.employee.EmployeeGetResponse;
import com.siwz.app.api.forms.employee.EmployeeUpdateRequest;
import com.siwz.app.persistence.dto.Employee;

public class EmployeeTranslator {

    public EmployeeGetResponse translateToApiGetResponse(Employee employee) {
        EmployeeGetResponse employeeGetResponse = new EmployeeGetResponse();
        employeeGetResponse.setFirstName(employee.getFirstName());
        employeeGetResponse.setLastName(employee.getLastname());
        return employeeGetResponse;
    }

    public EmployeeCreateResponse translateToApiCreateResponse(Long id) {
        EmployeeCreateResponse employeeCreateResponse = new EmployeeCreateResponse();
        employeeCreateResponse.setEmployeeId(id);
        return employeeCreateResponse;
    }

    public Employee translateToService(EmployeeCreateRequest employeeCreateRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeCreateRequest.getFirstName());
        employee.setLastname(employeeCreateRequest.getLastName());
        return employee;
    }

    public Employee translateToService(EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeUpdateRequest.getFirstName());
        employee.setLastname(employeeUpdateRequest.getLastName());
        employee.setSalary(employeeUpdateRequest.getSalary());
        return employee;
    }
}
