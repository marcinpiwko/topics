package com.siwz.app.services;

import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.repositories.SubjectRepository;
import com.siwz.app.persistence.repositories.TopicRepository;
import com.siwz.app.persistence.repositories.UserRepository;
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

    private final SubjectRepository subjectRepository;

    private final UserRepository userRepository;

    @Override // get all topics for specific subject
    public List<Topic> getTopicsBySubject(Long subjectId) throws ApplicationException {
        return topicRepository.findAllBySubject(subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId)));
    }

    @Override // get specific topic for specific subject
    public Topic getTopicById(Long topicId, Long subjectId) throws ApplicationException {
        return topicRepository.findByIdAndSubject(topicId, subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId)))
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_TOPIC_NOT_FOUND, topicId));
    }

    @Override // get user associated topic for specific subject
    public Topic getTopicByUserAndSubject(Long userId, Long subjectId) throws ApplicationException {
        if(!subjectRepository.existsById(subjectId)) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        if(!userRepository.existsById(userId)) {
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        return topicRepository.findByUserAndSubject(userId, subjectId).orElseThrow(() -> new ApplicationException(DAOError.DAO_NOT_ASSIGNED_TOPIC, userId, subjectId));
    }

    @Override // get all user associated topic
    public List<Topic> getTopicsByUser(Long userId) throws ApplicationException {
        if(!userRepository.existsById(userId)) {
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        return topicRepository.findByUser(userId);
    }

}
