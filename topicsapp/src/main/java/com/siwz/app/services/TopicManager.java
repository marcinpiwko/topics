package com.siwz.app.services;

import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.repositories.TopicRepository;
import com.siwz.app.services.interfaces.TopicService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicManager implements TopicService {

    private final TopicRepository topicRepository;

    @Override // get all topics for specific subject
    public List<Topic> getTopicsBySubject(Long subjectId) {
        return topicRepository.findAllBySubject(subjectId);
    }

    @Override // get specific topic for specific subject
    public Topic getTopicById(Long topicId, Long subjectId) throws ApplicationException {
        return topicRepository.findByIdAndSubject(topicId, subjectId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_TOPIC_NOT_FOUND, topicId));
    }

    @Override // get user associated topic for specific subject
    public Topic getTopicByUserAndSubject(Long userId, Long subjectId) throws ApplicationException {
        return topicRepository.findByUserAndSubject(userId, subjectId).orElseThrow(() -> new ApplicationException(DAOError.DAO_ITEM_NOT_FOUND, userId));
    }

    @Override // get all user associated topic
    public List<Topic> getTopicsByUser(Long userId) {
        return topicRepository.findByUser(userId);
    }

}
