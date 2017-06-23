package com.apo.security;

import com.apo.model.user.User;
import com.apo.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 22/06/2017.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = (String)authentication.getCredentials();
        User user = repository.findByName(name);

        if (user == null || !user.getUsername().equalsIgnoreCase(name)) {
            throw new BadCredentialsException("Username not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Passwords don't match");
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(name, password, user.getAuthorities());
        token.setDetails(user);
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
