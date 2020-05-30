package com.siwz.app.api.controllers.utils;

import com.siwz.app.api.forms.ErrorResponse;
import com.siwz.app.api.forms.ResponseForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    private ApiResponse() {

    }

    public static ResponseEntity<? extends ResponseForm> notFound(String message) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.toString(), message), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<? extends ResponseForm> badRequest(String message) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<? extends ResponseForm> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<? extends ResponseForm> ok(ResponseForm form) {
        return new ResponseEntity<>(form, HttpStatus.OK);
    }
}
