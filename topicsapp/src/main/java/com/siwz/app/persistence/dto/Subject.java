package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUB_ID")
    private Long id;

    @Column(name = "SUB_NAME")
    private String name;

    @Column(name = "SUB_DESCRIPTION")
    private String description;

    @Column(name = "SUB_TEACHER") // TODO foreign key ONLY ADMIN
    private Long userId;

    @Column(name = "SUB_ECTS")
    private Integer ects;
}
