package com.siwz.app.services.interfaces;

import com.siwz.app.persistence.model.Topic;
import com.siwz.app.persistence.model.User;
import com.siwz.app.utils.errors.ApplicationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Long createUser(User user) throws ApplicationException;

    void updateUser(Long userId, User newUser) throws ApplicationException;

    void deleteUser(Long userId) throws ApplicationException;

    List<User> getUsersByRole(String role) throws ApplicationException;

    User getUserById(Long userId) throws ApplicationException;

    Topic getUserAssignedTopicBySubject(Long userId, Long subjectId) throws ApplicationException;

    List<Topic> getAllUserAssignedTopics(Long userId) throws ApplicationException;
}
