package com.apo.controllers;

import com.apo.db.MongoService;
import com.apo.error.RequestParamNotFoundException;
import com.apo.response.Response;
import com.apo.util.ErrorMessages;
import com.apo.response.PointResponse;
import com.apo.ws.WebSocketSessionManager;
import com.apo.model.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@RestController()
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private Desk desk;
    @Autowired
    private WebSocketSessionManager socketSessionManager;
    @Autowired
    private MongoService service;

    @GetMapping("/desk")
    public Desk getDesk() {
        return desk;
    }

    @GetMapping("/draw") //TODO: change to POST
    public Response setPoint(@RequestParam(value = "x", required = false) Integer x,
                                  @RequestParam(value = "y", required = false) Integer y,
                                  @RequestParam(value = "color", required = false) Byte color) {
        checkRequestParams(x, y, color);
        desk.setPoint(x, y, color);
        Response response = new PointResponse.Builder()
                .setColor(color)
                .setX(x)
                .setY(y)
                .build();
        socketSessionManager.sendToAll(response);
        service.updateDesk(desk);
        return response;

    }

    @GetMapping("/get")
    public Response getPoint(@RequestParam("x") Integer x, @RequestParam("y") Integer y) {
        Byte color = desk.getPoint(x, y);
        Response response = new PointResponse.Builder()
                .setColor(color)
                .setX(x)
                .setY(y)
                .build();
        return response;
    }

    private void checkRequestParams(Integer x, Integer y, Byte color) {
        if (x == null) {
            throw new RequestParamNotFoundException(ErrorMessages.X_NOT_FOUND);
        }
        if (y == null) {
            throw new RequestParamNotFoundException(ErrorMessages.Y_NOT_FOUND);
        }
        if (color == null) {
            throw new RequestParamNotFoundException(ErrorMessages.COLOR_NOT_FOUND);
        }
    }
}
