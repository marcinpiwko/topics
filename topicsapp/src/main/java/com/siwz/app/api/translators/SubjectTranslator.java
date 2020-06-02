package com.siwz.app.api.translators;

import com.siwz.app.api.forms.subject.SubjectCreateRequest;
import com.siwz.app.api.forms.subject.SubjectGetResponse;
import com.siwz.app.api.forms.subject.SubjectUpdateRequest;
import com.siwz.app.persistence.dto.Subject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubjectTranslator {

    private final UserTranslator userTranslator;

    public Subject translateToService(SubjectCreateRequest subjectCreateRequest) {
        Subject subject = new Subject();
        subject.setName(subjectCreateRequest.getName());
        subject.setDescription(subjectCreateRequest.getDescription());
        subject.setEcts(subjectCreateRequest.getEcts());
        return subject;
    }

    public Subject translateToService(SubjectUpdateRequest subjectUpdateRequest) {
        Subject subject = new Subject();
        subject.setName(subjectUpdateRequest.getName());
        subject.setDescription(subjectUpdateRequest.getDescription());
        subject.setEcts(subjectUpdateRequest.getEcts());
        return subject;
    }

    public SubjectGetResponse translateToSubjectGetResponse(Subject subject) {
        SubjectGetResponse subjectGetResponse = new SubjectGetResponse();
        subjectGetResponse.setName(subject.getName());
        subjectGetResponse.setDescription(subject.getDescription());
        subjectGetResponse.setId(subject.getId());
        subjectGetResponse.setTeacher(userTranslator.translateToUserGetResponse(subject.getUser()));
        subjectGetResponse.setEcts(subject.getEcts());
        return subjectGetResponse;
    }
}
