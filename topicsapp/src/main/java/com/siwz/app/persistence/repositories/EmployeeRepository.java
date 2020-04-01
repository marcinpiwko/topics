package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
