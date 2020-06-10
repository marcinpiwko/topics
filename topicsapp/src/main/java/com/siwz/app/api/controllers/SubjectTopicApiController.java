package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.common.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.topic.TopicCreateRequest;
import com.siwz.app.api.forms.topic.TopicUpdateRequest;
import com.siwz.app.api.forms.topic.TopicsGetResponse;
import com.siwz.app.api.interfaces.SubjectTopicApi;
import com.siwz.app.api.translators.TopicTranslator;
import com.siwz.app.services.interfaces.SubjectService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SubjectTopicApiController implements SubjectTopicApi {

    private final SubjectService subjectService;

    private final TopicTranslator topicTranslator;

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> createSubjectTopic(Long subjectId, @Valid TopicCreateRequest topicCreateRequest) {
        try {
            return ApiResponse.ok(new IdResponse(subjectService.createSubjectTopic(subjectId, topicTranslator.translateToService(topicCreateRequest))));
        } catch(ApplicationException e) {
            if(DAOError.DAO_SUBJECT_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> updateSubjectTopic(Long subjectId, Long topicId, @Valid TopicUpdateRequest topicUpdateRequest) {
        try {
            subjectService.updateSubjectTopic(subjectId, topicId, topicTranslator.translateToService(topicUpdateRequest));
            return ApiResponse.noContent();
        } catch(ApplicationException e) {
            return handleApplicationException(e);
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> deleteSubjectTopic(Long subjectId, Long topicId) {
        try {
            subjectService.deleteSubjectTopic(subjectId, topicId);
            return ApiResponse.noContent();
        } catch(ApplicationException e) {
            return handleApplicationException(e);
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> getSubjectTopic(Long subjectId, Long topicId) {
        try {
            return ApiResponse.ok(topicTranslator.translateToTopicGetResponse(subjectService.getSubjectTopicById(subjectId, topicId)));
        } catch(ApplicationException e) {
            return handleApplicationException(e);
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> getSubjectTopics(Long subjectId) {
        try {
            return ApiResponse.ok(new TopicsGetResponse(subjectService.getSubjectTopics(subjectId)
                    .stream()
                    .map(topicTranslator::translateToTopicGetResponse)
                    .collect(Collectors.toList())));
        } catch(ApplicationException e) {
            return handleApplicationException(e);
        }
    }

    private ResponseEntity<? extends ResponseForm> handleApplicationException(ApplicationException e) {
        if(DAOError.DAO_SUBJECT_NOT_FOUND.equals(e.getErrorCode()) || DAOError.DAO_TOPIC_NOT_FOUND.equals(e.getErrorCode())) {
            return ApiResponse.notFound(e.getMessage());
        }
        return ApiResponse.badRequest(e.getMessage());
    }
}
