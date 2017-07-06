package com.apo.controllers;

import com.apo.model.user.User;
import com.apo.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 14/06/2017.
 */
@Controller
@RequestMapping("/")
public class ViewController {
    @Autowired UserRepository repository;

    @GetMapping("/")
    public String home(Model model, Authentication auth){
        if (auth != null) {
            User user = (User)auth.getDetails();
            model.addAttribute("user", user);
        }
        return "home";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("desk")
    public String desk(Model model, Authentication auth) {
        if (auth != null) {
            User user = (User)auth.getDetails();
            model.addAttribute("user", user);
        }
        return "desk";
    }
}
