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
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
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
            log.warn("reserveTopic with id: " + topicId + " for userId: " + userId + " and subjectId: " + subjectId + " failed");
            throw new ApplicationException(DAOError.DAO_USER_SUBJECT_TOPIC_EXISTS, user.getId(), subjectId);
        }
        Long reservationId = executeReservation(topic, user);
        log.info("reserveTopic for user id: "
                + userId + " and subject id: "
                + subjectId + " and topic id: "
                + topicId + ", reservation id: "
                + reservationId);
        return reservationId;
    }

    @Override
    public void deleteTopicReservation(Long userId, Long subjectId, Long topicId) throws ApplicationException {
        Topic topic = topicService.getTopicById(topicId, subjectService.getSubjectById(subjectId));
        User user = userService.getUserById(userId);
        if(!topicReservationRepository.existsByTopicAndStudent(topic, user)) {
            log.warn("deleteTopicReservation with id: " + topicId + " for userId: " + userId + " and subjectId: " + subjectId + " failed");
            throw new ApplicationException(DAOError.DAO_TOPIC_RESERVATION_NOT_FOUND, topicId, userId);
        }
        topicReservationRepository.deleteByTopicAndStudent(topic, user);
        log.info("deleteTopicReservation for user id: "
                + userId + " and subject id: "
                + subjectId + " and topic id: "
                + topicId);
    }

    private boolean validate(Topic topic, User user, Long subjectId) throws ApplicationException {
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
        topicReservationRepository.save(topicReservation);
        return topicReservation.getId();
    }
}
