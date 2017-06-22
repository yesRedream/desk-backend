package com.apo.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCoordinatesException extends RuntimeException {
    private static String MSG = "X and Y must be in range [0; DESK_SIZE)";
    public InvalidCoordinatesException() {
        super(MSG);
    }
}
