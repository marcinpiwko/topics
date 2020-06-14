package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.model.Topic;
import com.siwz.app.persistence.model.TopicReservation;
import com.siwz.app.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicReservationRepository extends JpaRepository<TopicReservation, Long> {

    Long countByTopic(Topic topic);

    boolean existsByTopicAndStudent(Topic topic, User user);

    void deleteByTopicAndStudent(Topic topic, User user);

}
