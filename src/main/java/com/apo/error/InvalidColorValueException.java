package com.apo.error;

import com.apo.util.Constants;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */
public class InvalidColorValueException extends RuntimeException {
    private static String MSG = "Color must be in range ["
            + Constants.MIN_COLOR_VALUE + "; "
            + Constants.MAX_COLOR_VALUE + "]";

    public InvalidColorValueException() {
        super(MSG);
    }
}
