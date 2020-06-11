package com.siwz.app.services.implementation;

import com.siwz.app.persistence.model.Topic;
import com.siwz.app.persistence.model.TopicReservation;
import com.siwz.app.persistence.model.User;
import com.siwz.app.persistence.repositories.TopicReservationRepository;
import com.siwz.app.services.interfaces.SubjectService;
import com.siwz.app.services.interfaces.TopicReservationService;
import com.siwz.app.services.interfaces.TopicService;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.DateTimeUtil;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicReservationManager implements TopicReservationService {

    private final TopicReservationRepository topicReservationRepository;

    private final TopicService topicService;

    private final SubjectService subjectService;

    private final UserService userService;

    @Override
    public Long reserveTopic(Long userId, Long subjectId, Long topicId) throws ApplicationException {
        Topic topic = topicService.getTopicById(topicId, subjectService.getSubjectById(subjectId));
        User user = userService.getUserById(userId);
        if(!validate(topic, user, subjectId)) {
            throw new ApplicationException(DAOError.DAO_USER_SUBJECT_TOPIC_EXISTS, user.getId(), subjectId);
        }
        return executeReservation(topic, user);
    }

    @Override
    public void deleteTopicReservation(Long userId, Long subjectId, Long topicId) throws ApplicationException {
        Topic topic = topicService.getTopicById(topicId, subjectService.getSubjectById(subjectId));
        User user = userService.getUserById(userId);
        if(!topicReservationRepository.existsByTopicAndStudent(topic, user)) {
            throw new ApplicationException(DAOError.DAO_TOPIC_RESERVATION_NOT_FOUND, topicId, userId);
        }
        topicReservationRepository.deleteByTopicAndStudent(topic, user);
    }

    private Boolean validate(Topic topic, User user, Long subjectId) throws ApplicationException {
        if((topic.getLimit() - topicReservationRepository.countByTopic(topic)) == 0) {
            throw new ApplicationException(DAOError.DAO_TOPIC_REACHED_LIMIT, topic.getId());
        }
        try {
            topicService.getTopicByUserAndSubject(user.getId(), subjectId);
            return false;
        } catch(ApplicationException e) {
            return true;
        }
    }

    private Long executeReservation(Topic topic, User user) {
        TopicReservation topicReservation = new TopicReservation();
        topicReservation.setTopic(topic);
        topicReservation.setStudent(user);
        topicReservation.setReservationDate(DateTimeUtil.getCurrentDate());
        topicReservationRepository.save(topicReservation);
        return topicReservation.getId();
    }
}
