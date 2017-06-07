package com.apo.response;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
public class JsonPointResponseBuilder {
    private Status status = null;
    private short color = -1;
    private int x = -1;
    private int y = -1;
    private String msg = null;

    public JsonPointResponseBuilder() {
    }

    public Status getStatus() {
        return status;
    }

    public JsonPointResponseBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public short getColor() {
        return color;
    }

    public JsonPointResponseBuilder setColor(short color) {
        this.color = color;
        return this;
    }

    public int getX() {
        return x;
    }

    public JsonPointResponseBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public JsonPointResponseBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public JsonPointResponse build() {
        return new JsonPointResponse(this);
    }

    public String getMsg() {
        return msg;
    }

    public JsonPointResponseBuilder setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
