package com.siwz.app.services.interfaces;

import com.siwz.app.utils.errors.ApplicationException;

public interface TopicReservationService {

    Long reserveTopic(Long userId, Long subjectId, Long topicId) throws ApplicationException;

    void deleteTopicReservation(Long userId, Long subjectId, Long topicId) throws ApplicationException;
}
