package com.siwz.app.persistence.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TOPIC_RESERVATIONS")
public class TopicReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRS_ID")
    private Long id;

    @Column(name = "TRS_TOP_ID") // TODO foreign key
    private Long topicId;

    @Column(name = "TRS_USR_ID") // TODO foreign key
    private Long userId;

    @Column(name = "TRS_RESERVATION_DATE")
    private Date reservationDate;

}
