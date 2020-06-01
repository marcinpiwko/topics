package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.topic.TopicsGetResponse;
import com.siwz.app.api.interfaces.UserTopicApi;
import com.siwz.app.api.translators.TopicTranslator;
import com.siwz.app.services.interfaces.TopicReservationService;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserTopicApiController implements UserTopicApi {

    private final UserService userService;

    private final TopicReservationService topicReservationService;

    private final TopicTranslator topicTranslator;

    @Override
    public ResponseEntity<? extends ResponseForm> getUserAssignedTopic(Long userId, Long subjectId) {
        try {
            return ApiResponse.ok(topicTranslator.translateToTopicGetResponse(userService.getUserAssignedTopicBySubject(userId, subjectId)));
        } catch(ApplicationException e) {
            if(DAOError.DAO_USER_NOT_FOUND.equals(e.getErrorCode()) || DAOError.DAO_SUBJECT_NOT_FOUND.equals(e.getErrorCode())) {
                ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> getAllUserAssignedTopics(Long userId) {
        try {
            return ApiResponse.ok(new TopicsGetResponse(userService.getAllUserAssignedTopics(userId)
                    .stream()
                    .map(topicTranslator::translateToTopicGetResponse)
                    .collect(Collectors.toList())));
        } catch(ApplicationException e) {
            if(DAOError.DAO_USER_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> reserveTopic(Long userId, Long subjectId, Long topicId) {
        try {
            return ApiResponse.ok(new IdResponse(topicReservationService.reserveTopic(userId, subjectId, topicId)));
        } catch(ApplicationException e) {
            if(DAOError.DAO_USER_SUBJECT_TOPIC_EXISTS.equals(e.getErrorCode())) {
                return ApiResponse.badRequest(e.getMessage());
            }
            return ApiResponse.notFound(e.getMessage());
        }
    }
}
