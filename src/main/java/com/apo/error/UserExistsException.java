package com.apo.error;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 06/07/2017.
 */
public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super(ErrorMessages.USER_EXISTS);
    }
}
