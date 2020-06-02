package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TOPIC_RESERVATIONS")
public class TopicReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TOPIC_RES_ID_GENERATOR")
    @SequenceGenerator(name = "TOPIC_RES_ID_GENERATOR", sequenceName = "TOPIC_RES_SEQ")
    @Column(name = "TRS_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "TOP_ID", name = "TRS_TOP_ID", nullable = false)
    private Topic topic;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "USR_ID", name = "TRS_USR_ID", nullable = false)
    private User user;

    @Column(name = "TRS_RESERVATION_DATE", nullable = false)
    private Date reservationDate;

}
