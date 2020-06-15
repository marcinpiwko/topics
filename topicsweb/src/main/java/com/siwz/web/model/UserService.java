package com.siwz.web.model;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UserService implements Serializable {
    private static Log log = LogFactory.getLog(Serializable.class);


    private String previousHandle;

    public void store(User user) throws ServiceException {

        // Here you can store the object into the DB, call REST services, etc.

        // for demo purposes, always fail first try
        if (previousHandle == null || !previousHandle.equals(user.getHandle())) {
            previousHandle = user.getHandle();
            throw new ServiceException("This exception simulates an error in the backend, and is intentional. Please try to submit the form again.");
        }
    }
    public static class ServiceException extends Exception {
        public ServiceException(String msg) {
            super(msg);
        }
    }
}
