package com.apo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonPointResponse {
    @JsonProperty("status")
    private Status status;
    @JsonProperty("color")
    private short color;
    @JsonProperty("x")
    private int x;
    @JsonProperty("y")
    private int y;
    @JsonProperty("msg")
    private String msg;

    public JsonPointResponse(JsonPointResponseBuilder builder) {
        this.status = builder.getStatus();
        this.color = builder.getColor();
        this.x = builder.getX();
        this.y = builder.getY();
        this.msg = builder.getMsg();
    }

    public JsonPointResponse() {
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setColor(short color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
