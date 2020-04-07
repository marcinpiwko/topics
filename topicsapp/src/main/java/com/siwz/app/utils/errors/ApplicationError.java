package com.siwz.app.utils.errors;

public interface ApplicationError {

    String name();

    int ordinal();

    String getMessage();
}
