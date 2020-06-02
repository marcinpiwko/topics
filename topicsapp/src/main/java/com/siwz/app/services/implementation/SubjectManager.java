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

    @Override
    public Long createSubject(Subject subject) throws ApplicationException {
        if(subjectRepository.existsByName(subject.getName())) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_ALREADY_EXISTS, subject.getName());
        }
        subjectRepository.save(subject);
        return subject.getId();
    }

    @Override
    public void updateSubject(Long subjectId, Subject newSubject) throws ApplicationException {
        Optional<Subject> originalSubject = subjectRepository.findById(subjectId);
        if(!originalSubject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        updateSubjectData(originalSubject.get(), newSubject);
    }

    @Override
    public void deleteSubject(Long subjectId) throws ApplicationException {
        if(!subjectRepository.existsById(subjectId)) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long subjectId) throws ApplicationException {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId));
    }

    @Override
    public Long createSubjectTopic(Long subjectId, Topic topic) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topic.setSubject(subject.get());
        return topicService.createTopic(topic);
    }

    @Override
    public void updateSubjectTopic(Long subjectId, Long topicId, Topic topic) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topic.setSubject(subject.get());
        topicService.updateTopic(topicId, topic);
    }

    @Override
    public void deleteSubjectTopic(Long subjectId, Long topicId) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topicService.deleteTopic(topicId, subject.get());
    }

    @Override
    public List<Topic> getSubjectTopics(Long subjectId) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        return topicService.getTopics(subject.get());
    }

    @Override
    public Topic getSubjectTopicById(Long subjectId, Long topicId) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        return topicService.getTopicById(topicId, subject.get());
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
