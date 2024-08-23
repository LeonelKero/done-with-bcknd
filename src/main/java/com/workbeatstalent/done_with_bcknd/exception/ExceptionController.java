package com.workbeatstalent.done_with_bcknd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public record ExceptionController() {

    @ExceptionHandler(value = {ResourceConflictException.class})
    public ResponseEntity<String> resourceConflictHandler(final ResourceConflictException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<String> resourceNotFoundHandler(final ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
