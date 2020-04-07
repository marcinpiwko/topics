package com.siwz.app.services;

import com.siwz.app.persistence.dto.Employee;
import com.siwz.app.persistence.repositories.EmployeeRepository;
import com.siwz.app.services.interfaces.EmployeeService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployee(Long id) throws ApplicationException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()) {
           throw new ApplicationException(DAOError.DAO_ITEM_NOT_FOUND, id);
        }
        return employee.get();
    }

    @Override
    public Long createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee.getId();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) throws ApplicationException {
        Optional<Employee> currentEmployee = employeeRepository.findById(id);
        if(!currentEmployee.isPresent()) {
            throw new ApplicationException(DAOError.DAO_ITEM_NOT_FOUND, id);
        }
        updateEmployeeData(currentEmployee.get(), employee);
    }

    @Override
    public void deleteEmployee(Long id) throws ApplicationException {
        if(!employeeRepository.existsById(id)) {
            throw new ApplicationException(DAOError.DAO_ITEM_NOT_FOUND, id);
        }
        employeeRepository.deleteById(id);
    }

    private void updateEmployeeData(Employee before, Employee after) {
        if(after.getFirstName() != null) {
            before.setFirstName(after.getFirstName());
        }
        if(after.getLastname() != null) {
            before.setLastname(after.getLastname());
        }
        employeeRepository.save(before);
    }
}
