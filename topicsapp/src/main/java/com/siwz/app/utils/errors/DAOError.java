package com.siwz.app.utils.errors;

public enum DAOError implements ApplicationError {

    DAO_ITEM_NOT_FOUND("Not found item by id: {0}"),
    DAO_INVALID_CREDENTIALS("Invalid username or password"),

    DAO_USER_ALREADY_EXISTS("User {0} already exists"),
    DAO_SUBJECT_ALREADY_EXISTS("Subject {0} already exists"),

    DAO_USER_NOT_FOUND("User {0} not found"),
    DAO_SUBJECT_NOT_FOUND("Subject {0} not found"),
    DAO_TOPIC_NOT_FOUND("Topic {0} not found")
    ;

    String message;

    DAOError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
