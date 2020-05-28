package com.siwz.app.services;

import com.siwz.app.persistence.dto.Role;
import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.persistence.dto.User;
import com.siwz.app.persistence.repositories.RoleRepository;
import com.siwz.app.persistence.repositories.UserRepository;
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

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TopicService topicService;

    @Override
    public void createUser(User user) throws ApplicationException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ApplicationException(DAOError.DAO_USER_ALREADY_EXISTS, user.getEmail());
        }
        user.setRole(roleRepository.findByType(Role.RoleType.STUDENT));
        userRepository.save(user);
    }

    @Override
    public List<User> getUsersByRole(Role.RoleType roleType) { // TODO api endpoint GET /users with required query param role=type
        return userRepository.findByRole(roleRepository.findByType(roleType));
    }

    @Override
    public User getUserById(Long userId) throws ApplicationException { // TODO api endpoint GET /users/{id}
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_USER_NOT_FOUND, userId));
    }

    @Override
    public Topic getUserAssignedTopicBySubject(Long userId, Long subjectId) throws ApplicationException { // TODO api endpoint GET /users/{id}/subjects/{id}/topic
        return topicService.getTopicByUserAndSubject(userId, subjectId);
    }

    @Override
    public List<Topic> getAllUserAssignedTopics(Long userId) { // TODO api endpoint GET /users/{id}/topics
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
}
