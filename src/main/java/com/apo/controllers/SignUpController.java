package com.apo.controllers;

import com.apo.error.UserExistsException;
import com.apo.model.user.User;
import com.apo.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public String getView(Model model, Authentication auth) {
        if (auth != null) {
            User user = (User)auth.getDetails();
            model.addAttribute("user", user);
        }
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping
    public String handleUserForm(@Valid @ModelAttribute("userForm") UserForm userForm,
                                 BindingResult bindingResult, HttpServletRequest request) {
        if (!bindingResult.hasErrors()) {
            boolean success = saveAndAuthorizeUser(userForm, request);
            if (success) {
                return "redirect:/desk";
            } else {
                return "redirect:/register"; //TODO: user messages
            }
        } else {
            return "redirect:/register";
        }
    }

    private boolean saveAndAuthorizeUser(UserForm userForm, HttpServletRequest request) {
        User user = buildNewUser(userForm);
        autoAuthenticateUser(user, request);

        return saveUser(user);
    }

    private boolean saveUser(User user) {
        try {
            userRepository.add(user);
        } catch (UserExistsException e) {
            return false;
        }
        return true;
    }

    private User buildNewUser(UserForm userForm) {
        return new User.Builder()
                .setUsername(userForm.getUsername())
                .setEmail(userForm.getEmail())
                .setPassword(encoder.encode(userForm.getPassword()))
                .addRole("ROLE_USER")
                .build();
    }

    private void autoAuthenticateUser(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();

        request.getSession();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        token.setDetails(user);

        SecurityContextHolder.getContext().setAuthentication(token);

    }
}
