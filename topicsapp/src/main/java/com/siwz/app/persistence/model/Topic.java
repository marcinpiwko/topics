package com.siwz.app.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TOPICS")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TOPIC_ID_GENERATOR")
    @SequenceGenerator(name = "TOPIC_ID_GENERATOR", sequenceName = "TOPIC_SEQ")
    @Column(name = "TOP_ID")
    private Long id;

    @Column(name = "TOP_NAME", nullable = false)
    private String name;

    @Column(name = "TOP_DESCRIPTION")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "SUB_ID", name = "TOP_SUB_ID", nullable = false)
    private Subject subject;

    @Column(name = "TOP_LIMIT", nullable = false)
    private Long limit;

    @Column(name = "TOP_CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name = "TOP_DEADLINE_DATE")
    private Date deadlineDate;

}
