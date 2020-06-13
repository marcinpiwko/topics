package com.siwz.web.repository.exceptions;

public class NonUniqueNameException extends Exception {
    @Override
    public String getMessage(){
        return "Taki użytkownik już istnieje";
    }
}
