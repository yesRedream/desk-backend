package com.apo.controllers;

import com.apo.error.RequestParamNotFoundException;
import com.apo.model.desk.DeskHolder;
import com.apo.model.desk.DeskService;
import com.apo.response.Response;
import com.apo.error.ErrorMessages;
import com.apo.response.PointResponse;
import com.apo.ws.WebSocketSessionManager;
import com.apo.model.desk.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@RestController()
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private WebSocketSessionManager socketSessionManager;
    @Autowired
    private DeskService deskService;
    @Autowired
    private DeskHolder deskHolder;

    @GetMapping("/desk")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Desk getDesk() {
        return deskHolder.getDesk();
    }

    @GetMapping("/draw") //TODO: change to POST
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Response setPoint(@RequestParam(value = "x", required = false) Integer x,
                                  @RequestParam(value = "y", required = false) Integer y,
                                  @RequestParam(value = "color", required = false) Byte color) {
        checkRequestParams(x, y, color);
        deskHolder.getDesk().setPoint(x, y, color);
        Response response = new PointResponse.Builder()
                .setColor(color)
                .setX(x)
                .setY(y)
                .build();
        socketSessionManager.sendToAll(response);
        deskService.updateDesk(deskHolder.getDesk());
        return response;

    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Response getPoint(@RequestParam("x") Integer x, @RequestParam("y") Integer y) {
        Byte color = deskHolder.getDesk().getPoint(x, y);
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
