package com.apo.controllers.signup;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/07/2017.
 */
public class UserForm {

    @NotNull
    @Size(min = 2, max = 30)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
