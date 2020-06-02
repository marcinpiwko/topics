package com.siwz.app.services.interfaces;

import com.siwz.app.persistence.dto.Subject;
import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.utils.errors.ApplicationException;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long subjectId) throws ApplicationException;

    List<Topic> getSubjectTopics(Long subjectId) throws ApplicationException;

    Topic getSubjectTopicById(Long subjectId, Long topicId) throws ApplicationException;

    Long createSubject(Subject subject) throws ApplicationException;

    void deleteSubject(Long subjectId) throws ApplicationException;

    void updateSubject(Long subjectId, Subject newSubject) throws ApplicationException;

    Long createSubjectTopic(Long subjectId, Topic topic) throws ApplicationException;

    void updateSubjectTopic(Long subjectId, Long topicId, Topic topic) throws ApplicationException;

    void deleteSubjectTopic(Long subjectId, Long topicId) throws ApplicationException;

    Boolean checkIfSubjectExists(Long subjectId);
}
