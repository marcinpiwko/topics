package com.siwz.app.api.translators;

import com.siwz.app.api.forms.user.UserCreateRequest;
import com.siwz.app.persistence.dto.User;
import com.siwz.app.utils.DateTimeUtil;
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
        user.setRegistrationDate(DateTimeUtil.getCurrentDate());
        return user;
    }
}
