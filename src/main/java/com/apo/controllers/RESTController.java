package com.apo.controllers;

import com.apo.response.JsonPointResponse;
import com.apo.response.JsonPointResponseBuilder;
import com.apo.response.Status;
import com.apo.ws.WebSocketSessionManager;
import com.apo.model.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@RestController(value = "/api")
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

    @GetMapping("/draw") //TODO: change to POST
    public JsonPointResponse setPoint(@RequestParam("x") Integer x,
                                      @RequestParam("y") Integer y, @RequestParam("color") byte color) {
        desk.setPoint(x, y, color);
        JsonPointResponse response = new JsonPointResponseBuilder()
                .setStatus(Status.OK)
                .setColor(color)
                .setX(x)
                .setY(y).build();
        socketSessionManager.sendToAll(response);
        return response;

    }

    @GetMapping("/get")
    public JsonPointResponse getPoint(@RequestParam("x") Integer x, @RequestParam("y") Integer y) {
        short color = desk.getPoint(x, y);
        JsonPointResponse response = new JsonPointResponseBuilder()
                .setStatus(Status.OK)
                .setColor(color)
                .setX(x)
                .setY(y).build();
        return response;
    }
}
