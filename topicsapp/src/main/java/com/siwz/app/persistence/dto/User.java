package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_ID_GENERATOR")
    @SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "USER_SEQ")
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_EMAIL", nullable = false)
    @Email
    private String email;

    @Column(name = "USR_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USR_FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "USR_LAST_NAME", nullable = false)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "ROL_ID", name = "USR_ROL_ID", nullable = false)
    private Role role;

    @Column(name = "USR_INDEX_NO")
    private String indexNo;

    @Column(name = "USR_REGISTRATION_DATE", nullable = false)
    private Date registrationDate;

}
