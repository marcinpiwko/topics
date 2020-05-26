package com.siwz.app.services.interfaces;

import com.siwz.app.utils.errors.ApplicationException;

public interface AuthService {

    String getUserToken(String username, String password) throws ApplicationException;
}
