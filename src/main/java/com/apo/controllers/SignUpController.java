package com.apo.controllers;

import com.apo.model.user.User;
import com.apo.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/07/2017.
 */
@Controller
@RequestMapping("register")
public class SignUpController {

    @GetMapping
    public String getView(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping
    public String handleUserForm(@Valid @ModelAttribute("userForm") UserForm userForm,
                                 BindingResult bindingResult, HttpServletRequest request) {
        if (!bindingResult.hasErrors()) {
            User user = buildNewUser(userForm);
            autoAuthenticateUser(user, request);
            //TODO: persist
            return "redirect:/desk";//TODO: user messages
        } else {
            return "redirect:/register";
        }
    }

    private User buildNewUser(UserForm userForm) {
        return new User.Builder()
                .setUsername(userForm.getUsername())
                .setEmail(userForm.getEmail())
                .setPassword(userForm.getPassword())//TODO: encrypt pass
                .addRole("ROLE_USER")
                .build();
    }

    private void autoAuthenticateUser(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();

        request.getSession();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, password, null);
        token.setDetails(user);

        SecurityContextHolder.getContext().setAuthentication(token);

    }
}
