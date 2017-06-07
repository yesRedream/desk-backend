package com.apo.error;

import com.apo.response.JsonPointResponse;
import com.apo.response.JsonPointResponseBuilder;
import com.apo.response.Status;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidCoordinatesException.class)
    public JsonPointResponse onInvalidCoordinatesException(Throwable throwable) {
        JsonPointResponse response = new JsonPointResponseBuilder()
                .setStatus(Status.ERROR)
                .setMsg(throwable.getMessage()).build();
        return response;
    }
}
