package com.siwz.app.services.interfaces;

import com.siwz.app.persistence.dto.Employee;

public interface EmployeeService {

    Employee getEmployee(Long id);

    void createEmployee(Employee employee);
}
