package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLES")
public class Role {

    public enum RoleType {
        STUDENT,
        TEACHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ROLE_ID_GENERATOR")
    @SequenceGenerator(name = "ROLE_ID_GENERATOR", sequenceName = "ROLE_SEQ")
    @Column(name = "ROL_ID")
    private Long id;

    @Column(name = "ROL_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType type;
}
