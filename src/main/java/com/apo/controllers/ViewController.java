package com.apo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

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
        return "about";
    }
}
