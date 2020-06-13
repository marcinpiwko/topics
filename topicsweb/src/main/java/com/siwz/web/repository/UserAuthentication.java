package com.siwz.web.repository;

import com.siwz.web.model.User;

public interface UserAuthentication {
    boolean checkAuthentication(User userRequest);

    void addNewUser(User userRequest) throws Exception;

    void signOut();
}
