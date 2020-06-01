package com.siwz.app.api.translators;

import com.siwz.app.api.forms.subject.SubjectCreateRequest;
import com.siwz.app.persistence.dto.Subject;

public class SubjectTranslator {

    public Subject translateToService(SubjectCreateRequest subjectCreateRequest) {
        Subject subject = new Subject();
        subject.setName(subjectCreateRequest.getName());
        subject.setDescription(subjectCreateRequest.getDescription());
        subject.setEcts(subjectCreateRequest.getEcts());
        return subject;
    }
}
