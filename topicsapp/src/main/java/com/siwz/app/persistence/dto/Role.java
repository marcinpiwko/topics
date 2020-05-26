package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private Long id;

    @Column(name = "ROL_NAME")
    private String name;
}
