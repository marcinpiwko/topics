package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TOPICS")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOP_ID")
    private Long id;

    @Column(name = "TOP_NAME")
    private String name;

    @Column(name = "TOP_DESCRIPTION")
    private String description;

    @Column(name = "TOP_SUB_ID") // TODO foreign key
    private Long subjectId;

    @Column(name = "TOP_CREATION_DATE")
    private Date creationDate;

    @Column(name = "TOP_DEADLINE_DATE")
    private Date deadlineDate;

    @Column(name = "TOP_REMOVE_DATE")
    private Date removeDate;

}
