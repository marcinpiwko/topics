package com.siwz.app.api.translators;

import com.siwz.app.api.forms.user.UserCreateRequest;
import com.siwz.app.api.forms.user.UserGetResponse;
import com.siwz.app.api.forms.user.UserUpdateRequest;
import com.siwz.app.persistence.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserTranslator {

    private final PasswordEncoder passwordEncoder;

    public User translateToService(UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setEmail(userCreateRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        user.setFirstName(userCreateRequest.getFirstName());
        user.setLastName(userCreateRequest.getLastName());
        user.setIndexNo(userCreateRequest.getIndexNo());
        return user;
    }

    public User translateToService(UserUpdateRequest userUpdateRequest) {
        User user = new User();
        user.setEmail(userUpdateRequest.getEmail());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setIndexNo(userUpdateRequest.getIndexNo());
        return user;
    }

    public UserGetResponse translateToUserGetResponse(User user) {
        UserGetResponse userGetResponse = new UserGetResponse();
        userGetResponse.setId(user.getId());
        userGetResponse.setEmail(user.getEmail());
        userGetResponse.setFirstName(user.getFirstName());
        userGetResponse.setLastName(user.getLastName());
        userGetResponse.setIndexNo(user.getIndexNo());
        return userGetResponse;
    }
}
