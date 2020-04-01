package com.siwz.app.api.translators;

import com.siwz.app.api.forms.EmployeeForm;
import com.siwz.app.persistence.dto.Employee;

public class EmployeeTranslator {

    public EmployeeForm translateToApi(Employee employee) {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setFirstName(employee.getFirstName());
        employeeForm.setLastName(employee.getLastname());
        return employeeForm;
    }

    public Employee translateToService(EmployeeForm employeeForm) {
        Employee employee = new Employee();
        employee.setFirstName(employeeForm.getFirstName());
        employee.setLastname(employeeForm.getLastName());
        return employee;
    }
}
