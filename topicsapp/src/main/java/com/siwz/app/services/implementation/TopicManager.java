package com.siwz.app.services.implementation;

import com.siwz.app.persistence.dto.Subject;
import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.repositories.TopicRepository;
import com.siwz.app.services.interfaces.TopicService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicManager implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public Long createTopic(Topic topic) throws ApplicationException {
        if(topicRepository.existsByName(topic.getName())) {
            throw new ApplicationException(DAOError.DAO_TOPIC_ALREADY_EXISTS, topic.getName());
        }
        topicRepository.save(topic);
        return topic.getId();
    }

    @Override
    public void updateTopic(Long topicId, Topic newTopic) throws ApplicationException {
        Optional<Topic> originalTopic = topicRepository.findByIdAndSubject(topicId, newTopic.getSubject());
        if(!originalTopic.isPresent()) {
            throw new ApplicationException(DAOError.DAO_TOPIC_NOT_FOUND, topicId);
        }
        updateTopicData(originalTopic.get(), newTopic);
    }

    @Override
    public void deleteTopic(Long topicId, Subject subject) throws ApplicationException {
        if(!topicRepository.existsByIdAndSubject(topicId, subject)) {
            throw new ApplicationException(DAOError.DAO_TOPIC_NOT_FOUND, topicId);
        }
        topicRepository.deleteByIdAndSubject(topicId, subject);
    }

    @Override // get all topics for specific subject
    public List<Topic> getTopics(Subject subject) {
        return topicRepository.findAllBySubject(subject);
    }

    @Override // get specific topic for specific subject
    public Topic getTopicById(Long topicId, Subject subject) throws ApplicationException {
        return topicRepository.findByIdAndSubject(topicId, subject)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_TOPIC_NOT_FOUND, topicId));
    }

    @Override // get user associated topic for specific subject
    public Topic getTopicByUserAndSubject(Long userId, Long subjectId) throws ApplicationException {
        return topicRepository.findByUserAndSubject(userId, subjectId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_NOT_ASSIGNED_TOPIC, userId, subjectId));
    }

    @Override // get all user associated topic
    public List<Topic> getTopicsByUser(Long userId) {
        return topicRepository.findByUser(userId);
    }

    private void updateTopicData(Topic originalTopic, Topic newTopic) throws ApplicationException {
        if(newTopic.getName() != null) {
            if(topicRepository.existsByName(newTopic.getName())) {
                throw new ApplicationException(DAOError.DAO_TOPIC_ALREADY_EXISTS, newTopic.getName());
            } else {
                originalTopic.setName(newTopic.getName());
            }
        }
        if(newTopic.getLimit() != null) {
            originalTopic.setLimit(newTopic.getLimit());
        }
        if(newTopic.getSubject() != null) {
            originalTopic.setSubject(newTopic.getSubject());
        }
        if(newTopic.getDescription() != null) {
            originalTopic.setDescription(newTopic.getDescription());
        }
        if(newTopic.getDeadlineDate() != null) {
            originalTopic.setDeadlineDate(newTopic.getDeadlineDate());
        }
        topicRepository.save(originalTopic);
    }
}
