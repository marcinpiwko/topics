package com.siwz.app.services.implementation;

import com.siwz.app.persistence.dto.Subject;
import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.repositories.SubjectRepository;
import com.siwz.app.services.interfaces.SubjectService;
import com.siwz.app.services.interfaces.TopicService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectManager implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final TopicService topicService;

    @Override // TAG subjects
    public Long createSubject(Subject subject) throws ApplicationException { // TODO api endpoint POST /subjects
        if(subjectRepository.existsByName(subject.getName())) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_ALREADY_EXISTS, subject.getName());
        }
        subjectRepository.save(subject);
        return subject.getId();
    }

    @Override // TAG subjects
    public void updateSubject(Long subjectId, Subject newSubject) throws ApplicationException { // TODO api endpoint PATCH /subjects/{id}
        Optional<Subject> originalSubject = subjectRepository.findById(subjectId);
        if(!originalSubject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        updateSubjectData(originalSubject.get(), newSubject);
    }

    @Override // TAG subjects
    public void deleteSubject(Long subjectId) { // TODO api endpoint DELETE /subjects/{id}
        subjectRepository.deleteById(subjectId);
    }

    @Override // TAG subjects
    public List<Subject> getAllSubjects() { // TODO api endpoint GET /subjects
        return subjectRepository.findAll();
    }

    @Override // TAG subjects
    public Subject getSubjectById(Long subjectId) throws ApplicationException { // TODO api endpoint GET /subjects/{id}
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId));
    }

    @Override // TAG subject-topics
    public List<Topic> getSubjectTopics(Long subjectId) throws ApplicationException { // TODO api endpoint GET /subjects/{id}/topics
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        return topicService.getTopics(subject.get());
    }

    @Override // TAG subject-topics
    public Topic getSubjectTopicById(Long subjectId, Long topicId) throws ApplicationException { // TODO api endpoint GET /subjects/{id}/topics/{id}
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        return topicService.getTopicById(topicId, subject.get());
    }

    @Override // TAG subject-topics
    public Long createSubjectTopic(Long subjectId, Topic topic) throws ApplicationException { // TODO api endpoint POST /subjects/{id}/topics
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topic.setSubject(subject.get());
        return topicService.createTopic(topic);
    }

    @Override // TAG subject-topics
    public void updateSubjectTopic(Long subjectId, Long topicId, Topic topic) throws ApplicationException { // TODO api endpoint PATCH /subjects/{id}/topics/{id}
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topic.setSubject(subject.get());
        topicService.updateTopic(topicId, topic);
    }

    @Override //TAG subject-topics
    public void deleteSubjectTopic(Long subjectId, Long topicId) throws ApplicationException { // TODO api endpoint DELETE /subjects/{id}/topics/{id}
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topicService.deleteTopic(topicId, subject.get());
    }

    @Override
    public Boolean checkIfSubjectExists(Long subjectId) {
        return subjectRepository.existsById(subjectId);
    }

    private void updateSubjectData(Subject originalSubject, Subject newSubject) {
        if(newSubject.getName() != null) {
            originalSubject.setName(newSubject.getName());
        }
        if(newSubject.getDescription() != null) {
            originalSubject.setDescription(newSubject.getDescription());
        }
        if(newSubject.getEcts() != null) {
            originalSubject.setEcts(newSubject.getEcts());
        }
        if(newSubject.getUser() != null) {
            originalSubject.setUser(newSubject.getUser());
        }
        subjectRepository.save(originalSubject);
    }
}
