package com.siwz.app.utils.errors;

public enum DAOError implements ApplicationError {

    DAO_INVALID_CREDENTIALS("Invalid username or password"),

    DAO_USER_ALREADY_EXISTS("User {0} already exists"),
    DAO_SUBJECT_ALREADY_EXISTS("Subject {0} already exists"),
    DAO_TOPIC_ALREADY_EXISTS("Topic {0} already exists"),

    DAO_USER_NOT_FOUND("User with id {0} not found"),
    DAO_SUBJECT_NOT_FOUND("Subject with id {0} not found"),
    DAO_TOPIC_NOT_FOUND("Topic with id {0} not found"),
    DAO_ROLE_NOT_FOUND("Role with id {0} not found"),
    DAO_TOPIC_RESERVATION_NOT_FOUND("Topic with id {0} reservation by user {1} not found"),

    DAO_NOT_ASSIGNED_TOPIC("No assigned topic to user {0} from subject {1}"),
    DAO_USER_SUBJECT_TOPIC_EXISTS("User {0} has already assigned topic from subject {1}"),
    DAO_TOPIC_REACHED_LIMIT("Topic {0} has reached limit of students"),
    DAO_TOPIC_ACTIVE_RESERVATIONS("Can not delete topic {0} with active reservations")
    ;

    String message;

    DAOError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
