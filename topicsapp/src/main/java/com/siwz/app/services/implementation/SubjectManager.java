package com.siwz.app.services.implementation;

import com.siwz.app.persistence.model.Subject;
import com.siwz.app.persistence.model.Topic;
import com.siwz.app.persistence.repositories.SubjectRepository;
import com.siwz.app.services.interfaces.SubjectService;
import com.siwz.app.services.interfaces.TopicService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubjectManager implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final TopicService topicService;

    @Override
    public Long createSubject(Subject subject) throws ApplicationException {
        if(subjectRepository.existsByName(subject.getName())) {
            log.warn("createSubject with name " + subject.getName() + " failed, subject already exists");
            throw new ApplicationException(DAOError.DAO_SUBJECT_ALREADY_EXISTS, subject.getName());
        }
        subjectRepository.save(subject);
        log.info("createSubject " + subject.getName() + ", new subject id: " + subject.getId());
        return subject.getId();
    }

    @Override
    public void updateSubject(Long subjectId, Subject newSubject) throws ApplicationException {
        Optional<Subject> originalSubject = subjectRepository.findById(subjectId);
        if(!originalSubject.isPresent()) {
            log.warn("updateSubject with id: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        updateSubjectData(originalSubject.get(), newSubject);
        log.info("updateSubject with id: " + subjectId);
    }

    @Override
    public void deleteSubject(Long subjectId) throws ApplicationException {
        if(!subjectRepository.existsById(subjectId)) {
            log.warn("deleteSubject with id " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        subjectRepository.deleteById(subjectId);
        log.info("deleteSubject with id: " + subjectId);
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        log.info("getAllSubjects, found: " + subjects.size());
        return subjects;
    }

    @Override
    public Subject getSubjectById(Long subjectId) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            log.warn("getSubjectById with id: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        log.info("getSubjectById with id: " + subjectId);
        return subject.get();
    }

    @Override
    public Long createSubjectTopic(Long subjectId, Topic topic) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            log.warn("createSubjectTopic with subject id: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topic.setSubject(subject.get());
        Long topicId = topicService.createTopic(topic);
        log.info("createSubjectTopic for subject id: " + subjectId + ", new topic id: " + topicId);
        return topicId;
    }

    @Override
    public void updateSubjectTopic(Long subjectId, Long topicId, Topic topic) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            log.warn("updateSubjectTopic with id: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topic.setSubject(subject.get());
        topicService.updateTopic(topicId, topic);
        log.info("updateSubjectTopic for subject id: " + subjectId + " and topic id: " + topicId);
    }

    @Override
    public void deleteSubjectTopic(Long subjectId, Long topicId) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            log.warn("deleteSubjectTopic with id: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        topicService.deleteTopic(topicId, subject.get());
        log.info("deleteSubjectTopic for subject id: " + subjectId + " and topic id: " + topicId);
    }

    @Override
    public List<Topic> getSubjectTopics(Long subjectId) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            log.warn("getSubjectTopics with id: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        List<Topic> topics = topicService.getTopics(subject.get());
        log.info("getSubjectTopics for subject id: " + subjectId + ", found " + topics.size() + " topics");
        return topics;
    }

    @Override
    public Topic getSubjectTopicById(Long subjectId, Long topicId) throws ApplicationException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(!subject.isPresent()) {
            log.warn("getSubjectTopicById with id: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        Topic topic = topicService.getTopicById(topicId, subject.get());
        log.info("getSubjectTopicById for subject id: " + subjectId + " and topic id: " + topicId);
        return topic;
    }

    @Override
    public boolean checkIfSubjectExists(Long subjectId) {
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
        if(newSubject.getTeacher() != null) {
            originalSubject.setTeacher(newSubject.getTeacher());
        }
        subjectRepository.save(originalSubject);
    }
}
