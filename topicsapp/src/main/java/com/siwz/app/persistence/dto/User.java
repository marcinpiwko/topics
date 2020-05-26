package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_FIRST_NAME")
    private String firstName;

    @Column(name = "USR_LAST_NAME")
    private String lastName;

    @Column(name = "USR_EMAIL") // login
    private String email;

    @Column(name = "USR_ROLE")
    private String role; // TODO with spring security

    @Column(name = "USR_INDEX_NO") // for students only
    private String indexNo;

}
