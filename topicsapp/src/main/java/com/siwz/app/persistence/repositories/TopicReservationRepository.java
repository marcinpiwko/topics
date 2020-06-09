package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.model.Topic;
import com.siwz.app.persistence.model.TopicReservation;
import com.siwz.app.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicReservationRepository extends JpaRepository<TopicReservation, Long> {

    Long countByTopic(Topic topic);

    Boolean existsByTopicAndUser(Topic topic, User user);

    void deleteByTopicAndUser(Topic topic, User user);

}
