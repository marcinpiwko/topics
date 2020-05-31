package com.siwz.app.services.implementation;

import com.siwz.app.persistence.dto.Role;
import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.dto.User;
import com.siwz.app.persistence.repositories.UserRepository;
import com.siwz.app.services.interfaces.RoleService;
import com.siwz.app.services.interfaces.TopicService;
import com.siwz.app.services.interfaces.UserService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final TopicService topicService;

    @Override // TAG USERS
    public Long createUser(User user) throws ApplicationException {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new ApplicationException(DAOError.DAO_USER_ALREADY_EXISTS, user.getEmail());
        }
        user.setRole(roleService.getRoleByType(Role.RoleType.STUDENT));
        userRepository.save(user);
        return user.getId();
    }

    @Override // TAG USERS
    public void updateUser(Long userId, User newUser) throws ApplicationException { // TODO api endpoint PATCH /users/{id}
        Optional<User> originalUser = userRepository.findById(userId);
        if(!originalUser.isPresent()) {
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        updateUserData(originalUser.get(), newUser);
    }

    @Override // TAG USERS
    public void deleteUser(Long userId) throws ApplicationException { // TODO api endpoint DELETE /users/{id}
        if(!userRepository.existsById(userId)) {
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        userRepository.deleteById(userId);
    }

    @Override // TAG USERS
    public List<User> getUsersByRole(Role.RoleType roleType) throws ApplicationException { // TODO api endpoint GET /users with required query param role=type
        if(!roleService.checkIfRoleExists(roleType)) {
            throw new ApplicationException(DAOError.DAO_ROLE_NOT_FOUND, roleType);
        }
        return userRepository.findByRole(roleService.getRoleByType(roleType));
    }

    @Override // TAG USERS
    public User getUserById(Long userId) throws ApplicationException { // TODO api endpoint GET /users/{id}
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId));
    }

    @Override // TAG USER-TOPICS
    public Topic getUserAssignedTopicBySubject(Long userId, Long subjectId) throws ApplicationException { // TODO api endpoint GET /users/{id}/subjects/{id}/topic
        if(!userRepository.existsById(userId)) {
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        return topicService.getTopicByUserAndSubject(userId, subjectId);
    }

    @Override // TAG USER-TOPICS
    public List<Topic> getAllUserAssignedTopics(Long userId) throws ApplicationException { // TODO api endpoint GET /users/{id}/topics
        if(!userRepository.existsById(userId)) {
            throw new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId);
        }
        return topicService.getTopicsByUser(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getType().name())));
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
