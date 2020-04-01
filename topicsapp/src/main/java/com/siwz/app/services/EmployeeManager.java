package com.siwz.app.services;

import com.siwz.app.persistence.dto.Employee;
import com.siwz.app.persistence.repositories.EmployeeRepository;
import com.siwz.app.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @PostConstruct
    private void createExampleEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Marcin");
        employee.setLastname("Piwko");
        employee.setSalary(6500.50);
        employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
