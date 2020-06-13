package com.siwz.web.repository.exceptions;

public class EmptyFieldException extends Exception {
    @Override
    public String getMessage(){
        return "Każde pole jest wymagane";
    }
}
