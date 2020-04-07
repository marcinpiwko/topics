package com.siwz.app.api.controllers.utils;

import com.siwz.app.api.forms.ErrorForm;
import com.siwz.app.api.forms.Form;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    private ApiResponse() {

    }

    public static ResponseEntity<? extends Form> notFound(Long id, String domain) {
        return new ResponseEntity<>(new ErrorForm(HttpStatus.NOT_FOUND.toString(), domain + " with id " + id + " not found"), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<? extends Form> badRequest() {
        return new ResponseEntity<>(new ErrorForm(HttpStatus.BAD_REQUEST.toString(), "Something bad happen with your request"), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<? extends Form> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<? extends Form> ok(Form form) {
        return new ResponseEntity<>(form, HttpStatus.OK);
    }
}
