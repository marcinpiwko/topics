package com.siwz.app.services.interfaces;

import com.siwz.app.persistence.dto.Employee;
import com.siwz.app.utils.errors.ApplicationException;

public interface EmployeeService {

    Employee getEmployee(Long id) throws ApplicationException;

    Long createEmployee(Employee employee);

    void updateEmployee(Long id, Employee employee) throws ApplicationException;

    void deleteEmployee(Long id) throws ApplicationException;
}
