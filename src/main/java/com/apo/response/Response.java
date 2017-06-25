package com.apo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 22/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Response {
    @JsonProperty("status")
    protected Status status;
    @JsonProperty("type")
    protected Type type;

    public Response(Status status, Type type) {
        this.status = status;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
