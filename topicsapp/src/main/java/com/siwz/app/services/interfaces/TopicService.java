package com.siwz.app.services.interfaces;

import com.siwz.app.persistence.model.Subject;
import com.siwz.app.persistence.model.Topic;
import com.siwz.app.utils.errors.ApplicationException;

import java.util.List;

public interface TopicService {

    Long createTopic(Topic topic) throws ApplicationException;

    void updateTopic(Long topicId, Topic newTopic) throws ApplicationException;

    void deleteTopic(Long topicId, Subject subject) throws ApplicationException;

    List<Topic> getTopics(Subject subject);

    Topic getTopicById(Long topicId, Subject subject) throws ApplicationException;

    Topic getTopicByUserAndSubject(Long userId, Long subjectId) throws ApplicationException;

    List<Topic> getTopicsByUser(Long userId);
}
