package com.felipemcassiano.UrlShortener.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundUrlException.class)
    public ResponseEntity<ErrorMessageDTO> handleNotFoundUrlException(NotFoundUrlException ex) {
        return new ResponseEntity<>(new ErrorMessageDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
