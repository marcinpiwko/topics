package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.dto.TopicReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicReservationRepository extends JpaRepository<TopicReservation, Long> {


}
