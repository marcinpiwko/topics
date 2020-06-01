package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.dto.TopicReservation;
import com.siwz.app.persistence.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicReservationRepository extends JpaRepository<TopicReservation, Long> {

    Long countByTopic(Topic topic);

}
