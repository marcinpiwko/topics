package com.siwz.app.services.implementation;

import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.dto.TopicReservation;
import com.siwz.app.persistence.dto.User;
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
        validate(topic, user, subjectId);
        return executeReservation(topic, user);
    }

    @Override
    public void deleteTopicReservation(Long id) throws ApplicationException {
        if(!topicReservationRepository.existsById(id)) {
            throw new ApplicationException(DAOError.DAO_TOPIC_NOT_FOUND, id);
        }
        topicReservationRepository.deleteById(id);
    }

    private void validate(Topic topic, User user, Long subjectId) throws ApplicationException {
        if((topic.getLimit() - topicReservationRepository.countByTopic(topic)) == 0) {
            throw new ApplicationException(DAOError.DAO_TOPIC_REACHED_LIMIT, topic.getId());
        }
        try {
            topicService.getTopicByUserAndSubject(user.getId(), subjectId);
            throw new ApplicationException(DAOError.DAO_USER_SUBJECT_TOPIC_EXISTS, user.getId(), subjectId);
        } catch(ApplicationException e) {

        }
    }

    private Long executeReservation(Topic topic, User user) {
        TopicReservation topicReservation = new TopicReservation();
        topicReservation.setTopic(topic);
        topicReservation.setUser(user);
        topicReservation.setReservationDate(DateTimeUtil.getCurrentDate());
        topicReservationRepository.save(topicReservation);
        return topicReservation.getId();
    }
}
