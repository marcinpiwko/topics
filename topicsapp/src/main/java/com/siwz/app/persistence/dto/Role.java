package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ROLES")
public class Role {
    // TODO with spring security
    // ADMIN - teacher
    // USER - student
}
