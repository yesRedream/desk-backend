package com.apo.controllers;

import com.apo.error.ErrorMessages;
import com.apo.error.RequestParamNotFoundException;
import com.apo.model.desk.Desk;
import com.apo.model.desk.DeskHolder;
import com.apo.model.desk.service.DeskService;
import com.apo.response.PointInfoResponse;
import com.apo.response.PointUpdateResponse;
import com.apo.response.Response;
import com.apo.ws.WebSocketSessionManager;
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

    @PutMapping("/draw")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Response draw(@RequestParam(value = "x", required = false) Integer x,
                         @RequestParam(value = "y", required = false) Integer y,
                         @RequestParam(value = "color", required = false) Byte color) {
        checkRequestParams(x, y, color);
        deskHolder.getDesk().setPoint(x, y, color);
        Response response = new PointUpdateResponse.Builder()
                .setColor(color)
                .setX(x)
                .setY(y)
                .build();
        socketSessionManager.broadcast(response);
        deskService.updateDesk(deskHolder.getDesk());
        return response;

    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Response getPoint(@RequestParam("x") Integer x, @RequestParam("y") Integer y) {
        Byte color = deskHolder.getDesk().getPoint(x, y);
        Response response = new PointInfoResponse.Builder()
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
