package com.siwz.app.services.interfaces;

import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.utils.errors.ApplicationException;

import java.util.List;

public interface TopicService {

    List<Topic> getTopicsBySubject(Long subjectId) throws ApplicationException;

    Topic getTopicById(Long topicId, Long subjectId) throws ApplicationException;

    Topic getTopicByUserAndSubject(Long userId, Long subjectId) throws ApplicationException;

    List<Topic> getTopicsByUser(Long userId) throws ApplicationException;
}
