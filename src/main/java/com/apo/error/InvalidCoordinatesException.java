package com.apo.error;


/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
public class InvalidCoordinatesException extends RuntimeException {
    public InvalidCoordinatesException() {
        super("X and Y must be in range [0; DESK_SIZE]");
    }
}
