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

    public Response(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
