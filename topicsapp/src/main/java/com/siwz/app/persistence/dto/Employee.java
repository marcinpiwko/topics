package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long id;

    @Column(name = "EMP_FIRST_NAME")
    private String firstName;

    @Column(name = "EMP_LAST_NAME")
    private String lastname;

    @Column(name = "EMP_SALARY")
    private Double salary;
}
