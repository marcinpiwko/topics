package com.siwz.app.services.implementation;

import com.siwz.app.persistence.model.Role;
import com.siwz.app.persistence.model.Topic;
import com.siwz.app.persistence.model.User;
import com.siwz.app.persistence.repositories.UserRepository;
import com.siwz.app.services.interfaces.RoleService;
import com.siwz.app.services.interfaces.SubjectService;
import com.siwz.app.services.interfaces.TopicService;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final TopicService topicService;

    private final SubjectService subjectService;

    @Override
    public Long createUser(User user) throws ApplicationException {
        if(userRepository.existsByEmail(user.getEmail())) {
            log.warn("createUser " + user.getEmail() + " failed, user already exists ");
            throw new ApplicationException(DAOError.DAO_USER_ALREADY_EXISTS, user.getEmail());
        }
        if(user.getIndexNo() != null && userRepository.existsByIndexNo(user.getIndexNo())) {
            log.warn("createUser with indexNo " + user.getIndexNo() + " failed, indexNo already exists");
            throw new ApplicationException(DAOError.DAO_USER_ALREADY_EXISTS, user.getIndexNo());
        }
        user.setRole(roleService.getRoleByType(Role.RoleType.STUDENT));
        userRepository.save(user);
        log.info("createUser " + user.getEmail() + ", new user id: " + user.getId());
        return user.getId();
    }

    @Override
    public void updateUser(Long userId, User newUser) throws ApplicationException {
        Optional<User> originalUser = userRepository.findById(userId);
        if(!originalUser.isPresent()) {
            log.warn("updateUser with id: " + userId + " failed, user not found");
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        updateUserData(originalUser.get(), newUser);
        log.info("updateUser for user id: " + userId);
    }

    @Override
    public void deleteUser(Long userId) throws ApplicationException {
        if(!userRepository.existsById(userId)) {
            log.warn("deleteUser with id: " + userId + " failed, user not found");
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        userRepository.deleteById(userId);
        log.info("deleteUser for user id: " + userId);
    }

    @Override
    public List<User> getUsersByRole(String roleType) throws ApplicationException {
        try {
            if(!roleService.checkIfRoleExists(Role.RoleType.valueOf(roleType))) {
                throw new IllegalArgumentException();
            }
            List<User> users = userRepository.findByRole(roleService.getRoleByType(Role.RoleType.valueOf(roleType)));
            log.info("getUsersByRole for role: " + roleType + ", found " + users.size() + " users");
            return users;
        } catch(IllegalArgumentException e) {
            log.warn("getUsersByRole with role: " + roleType + " failed, role not found");
            throw new ApplicationException(DAOError.DAO_ROLE_NOT_FOUND, roleType);
        }
    }

    @Override
    public User getUserById(Long userId) throws ApplicationException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            log.warn("getUserById with id: " + userId + " failed, user not found");
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        log.info("getUserById for user id: " + userId);
        return user.get();
    }

    @Override
    public Topic getUserAssignedTopicBySubject(Long userId, Long subjectId) throws ApplicationException {
        if(!userRepository.existsById(userId)) {
            log.warn("getUserAssignedTopicBySubject with userId: " + userId + " failed, user not found");
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        if(!subjectService.checkIfSubjectExists(subjectId)) {
            log.warn("getUserAssignedTopicBySubject with subjectId: " + subjectId + " failed, subject not found");
            throw new ApplicationException(DAOError.DAO_SUBJECT_NOT_FOUND, subjectId);
        }
        Topic topic = topicService.getTopicByUserAndSubject(userId, subjectId);
        log.info("getUserAssignedTopicBySubject for user id: " + userId + " and subject id: " + subjectId);
        return topic;
    }

    @Override
    public List<Topic> getAllUserAssignedTopics(Long userId) throws ApplicationException {
        if(!userRepository.existsById(userId)) {
            log.warn("getAllUserAssignedTopics with id: " + userId + " failed, user not found");
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        List<Topic> topics = topicService.getTopicsByUser(userId);
        log.info("getAllUserAssignedTopics for user id: " + userId + ", found " + topics.size() + " topics");
        return topics;
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    private void updateUserData(User originalUser, User newUser) throws ApplicationException {
        if(newUser.getEmail() != null) {
            if(!userRepository.existsByEmail(newUser.getEmail())) {
                originalUser.setEmail(newUser.getEmail());
            } else {
                throw new ApplicationException(DAOError.DAO_USER_ALREADY_EXISTS, newUser.getEmail());
            }
        }
        if(newUser.getFirstName() != null) {
            originalUser.setFirstName(newUser.getFirstName());
        }
        if(newUser.getIndexNo() != null) {
            originalUser.setIndexNo(newUser.getIndexNo());
        }
        if(newUser.getLastName() != null) {
            originalUser.setLastName(newUser.getLastName());
        }
        userRepository.save(originalUser);
    }
}
