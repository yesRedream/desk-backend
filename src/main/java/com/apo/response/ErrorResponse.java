package com.apo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 22/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends Response{
    @JsonProperty("error_message")
    private String errorMessage;

    public ErrorResponse(Builder builder) {
        super(Status.ERROR, Type.ERROR);
        this.errorMessage = builder.getErrorMessage();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class Builder implements ResponseBuilder {
        private String errorMessage;

        public String getErrorMessage() {
            return errorMessage;
        }

        public Builder setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        @Override
        public Response build() {
            return new ErrorResponse(this);
        }
    }
}
