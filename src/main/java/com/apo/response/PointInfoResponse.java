package com.apo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 25/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointInfoResponse extends Response{
    @JsonProperty("color")
    private Byte color;
    @JsonProperty("x")
    private Integer x;
    @JsonProperty("y")
    private Integer y;

    public PointInfoResponse(PointInfoResponse.Builder builder) {
        super(Status.OK, Type.POINT_INFO);
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

        public PointInfoResponse.Builder setColor(Byte color) {
            this.color = color;
            return this;
        }

        public Integer getX() {
            return x;
        }

        public PointInfoResponse.Builder setX(Integer x) {
            this.x = x;
            return this;
        }

        public Integer getY() {
            return y;
        }

        public PointInfoResponse.Builder setY(Integer y) {
            this.y = y;
            return this;
        }

        @Override
        public Response build() {
            return new PointInfoResponse(this);
        }
    }
}
