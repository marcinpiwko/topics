package com.siwz.app.utils.errors;

import com.siwz.app.utils.StringUtil;

public class ApplicationException extends Exception {

    private static final long serialVersionUID = 4390059691291269573L;

    private ApplicationError errorCode;

    private String message;

    private Object[] messageParams;

    public ApplicationException(ApplicationError errorCode, Object... msgParams) {
        this.errorCode = errorCode;
        this.message = StringUtil.replace(errorCode.getMessage(), msgParams);
        this.messageParams = msgParams;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }

    public void setMessageParams(Object[] messageParams) {
        this.messageParams = messageParams;
    }

    public ApplicationError getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        ApplicationError code = errorCode;
        String msg = message;
        String name = getClass().getName();
        if (msg == null) {
            return new StringBuffer(name.length() + 12).append(name).append(": [").append(code).append("]").toString();
        }
        else {
            return new StringBuffer(name.length() + 13 + msg.length()).append(name).append(": [").append(code).append("] ").append(msg).toString();
        }
    }
}
