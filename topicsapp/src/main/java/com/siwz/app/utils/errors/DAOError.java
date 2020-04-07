package com.siwz.app.utils.errors;

public enum DAOError implements ApplicationError {

    DAO_ITEM_NOT_FOUND("Not found item by id: {0}"),
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
