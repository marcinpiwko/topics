package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.common.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.subject.SubjectCreateRequest;
import com.siwz.app.api.forms.subject.SubjectUpdateRequest;
import com.siwz.app.api.forms.subject.SubjectsGetResponse;
import com.siwz.app.api.interfaces.SubjectApi;
import com.siwz.app.api.translators.SubjectTranslator;
import com.siwz.app.persistence.model.Subject;
import com.siwz.app.services.interfaces.SubjectService;
import com.siwz.app.services.interfaces.UserService;
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
public class SubjectApiController implements SubjectApi {

    private final SubjectService subjectService;

    private final UserService userService;

    private final SubjectTranslator subjectTranslator;

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> createSubject(@Valid SubjectCreateRequest subjectCreateRequest) {
        try {
            Subject subject = subjectTranslator.translateToService(subjectCreateRequest);
            subject.setTeacher(userService.getUserById(subjectCreateRequest.getUserId()));
            return ApiResponse.ok(new IdResponse(subjectService.createSubject(subject)));
        } catch (ApplicationException e) {
            if(DAOError.DAO_USER_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> updateSubject(Long id, @Valid SubjectUpdateRequest subjectUpdateRequest) {
        try {
            subjectService.updateSubject(id, subjectTranslator.translateToService(subjectUpdateRequest));
            return ApiResponse.noContent();
        } catch(ApplicationException e) {
            if(DAOError.DAO_SUBJECT_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<? extends ResponseForm> deleteSubject(Long id) {
        try {
            subjectService.deleteSubject(id);
            return ApiResponse.noContent();
        } catch(ApplicationException e) {
            if(DAOError.DAO_SUBJECT_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> getSubject(Long id) {
        try {
            return ApiResponse.ok(subjectTranslator.translateToSubjectGetResponse(subjectService.getSubjectById(id)));
        } catch(ApplicationException e) {
            if(DAOError.DAO_SUBJECT_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<? extends ResponseForm> getSubjects() {
        return ApiResponse.ok(new SubjectsGetResponse(subjectService.getAllSubjects()
                .stream()
                .map(subjectTranslator::translateToSubjectGetResponse)
                .collect(Collectors.toList())));
    }
}
