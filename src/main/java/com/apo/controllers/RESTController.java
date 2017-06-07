package com.apo.controllers;

import com.apo.ws.WebSocketSessionManager;
import com.apo.model.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@RestController(value = "/")
public class RESTController {

    @Autowired
    private Desk desk;
    @Autowired
    private WebSocketSessionManager socketSessionManager;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/desk")
    public Desk getDesk() {
        return desk;
    }

    @GetMapping("/set") //TODO: change to POST
    public void setPoint(@RequestParam("x") Integer x,
                           @RequestParam("y") Integer y, @RequestParam("color") Short color) {
        desk.setPoint(x, y, color);
    }

    @GetMapping("/get")
    public void getPoint(@RequestParam("x") Integer x,
                         @RequestParam("y") Integer y, @RequestParam("color") Short color) {
        desk.setPoint(x, y, color);
    }
}
