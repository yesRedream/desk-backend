package com.apo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointResponse extends Response{
    @JsonProperty("color")
    private Byte color;
    @JsonProperty("x")
    private Integer x;
    @JsonProperty("y")
    private Integer y;

    public PointResponse(Builder builder) {
        super(Status.OK);
        this.color = builder.getColor();
        this.x = builder.getX();
        this.y = builder.getY();
    }

    public void setColor(Byte color) {
        this.color = color;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public static class Builder implements ResponseBuilder {
        private Byte color;
        private Integer x;
        private Integer y;

        public Byte getColor() {
            return color;
        }

        public Builder setColor(Byte color) {
            this.color = color;
            return this;
        }

        public Integer getX() {
            return x;
        }

        public Builder setX(Integer x) {
            this.x = x;
            return this;
        }

        public Integer getY() {
            return y;
        }

        public Builder setY(Integer y) {
            this.y = y;
            return this;
        }

        @Override
        public Response build() {
            return new PointResponse(this);
        }
    }

}
