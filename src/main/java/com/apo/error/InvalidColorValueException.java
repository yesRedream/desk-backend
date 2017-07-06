package com.apo.error;

import com.apo.model.desk.Desk;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */
public class InvalidColorValueException extends RuntimeException {
    private static String MSG = "Color must be in range ["
            + Desk.MIN_COLOR_VALUE + "; "
            + Desk.MAX_COLOR_VALUE + "]";

    public InvalidColorValueException() {
        super(MSG);
    }
}
