package com.siwz.app.services;

import com.siwz.app.persistence.repositories.TopicReservationRepository;
import com.siwz.app.services.interfaces.TopicReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicReservationManager implements TopicReservationService {

    private final TopicReservationRepository topicReservationRepository;
}
