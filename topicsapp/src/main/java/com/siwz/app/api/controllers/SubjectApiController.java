package com.siwz.app.api.controllers;

import com.siwz.app.api.controllers.utils.ApiResponse;
import com.siwz.app.api.forms.IdResponse;
import com.siwz.app.api.forms.ResponseForm;
import com.siwz.app.api.forms.subject.SubjectCreateRequest;
import com.siwz.app.api.interfaces.SubjectApi;
import com.siwz.app.api.translators.SubjectTranslator;
import com.siwz.app.persistence.dto.Subject;
import com.siwz.app.services.interfaces.SubjectService;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SubjectApiController implements SubjectApi {

    private final SubjectService subjectService;

    private final UserService userService;

    private final SubjectTranslator subjectTranslator;

    @Override
    public ResponseEntity<? extends ResponseForm> createSubject(@Valid SubjectCreateRequest subjectCreateRequest) {
        try {
            Subject subject = subjectTranslator.translateToService(subjectCreateRequest);
            subject.setUser(userService.getUserById(subjectCreateRequest.getUserId()));
            return ApiResponse.ok(new IdResponse(subjectService.createSubject(subject)));
        } catch (ApplicationException e) {
            if(DAOError.DAO_USER_NOT_FOUND.equals(e.getErrorCode())) {
                return ApiResponse.notFound(e.getMessage());
            }
            return ApiResponse.badRequest(e.getMessage());
        }
    }
}
