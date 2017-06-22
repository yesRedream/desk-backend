package com.apo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 22/06/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestParamNotFoundException extends RuntimeException {
    public RequestParamNotFoundException(String message) {
        super(message);
    }
}
