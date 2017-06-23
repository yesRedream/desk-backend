package com.apo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import java.util.Collection;
import java.util.List;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 14/06/2017.
 */
@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("about")
    public String about() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return "about";
    }

    @GetMapping("desk")
    public String desk() {
        return "desk";
    }
}
