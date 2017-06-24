package com.apo.error;

import com.apo.response.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidCoordinatesException.class)
    public Response onInvalidCoordinatesException(Throwable throwable) {
        return new ErrorResponse.Builder()
                .setErrorMessage(throwable.getMessage())
                .build();
    }

    @ExceptionHandler(RequestParamNotFoundException.class)
    public Response onRequestParamNotFound(Throwable throwable) {
        return new ErrorResponse.Builder()
                .setErrorMessage(throwable.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidColorValueException.class)
    public Response onInvalidColorValue(Throwable throwable) {
        return new ErrorResponse.Builder()
                .setErrorMessage(throwable.getMessage())
                .build();
    }
}
