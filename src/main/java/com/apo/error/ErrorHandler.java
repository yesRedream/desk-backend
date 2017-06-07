package com.apo.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidCoordinatesException.class)
    public String onInvalidCoordinatesException() {
        return "heeeeq";
    }
}
