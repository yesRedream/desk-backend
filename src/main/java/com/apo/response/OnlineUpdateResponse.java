package com.apo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 25/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnlineUpdateResponse extends Response {
    @JsonProperty("online_count")
    private Integer onlineCount;

    public OnlineUpdateResponse(Builder builder) {
        super(Status.OK, Type.ONLINE_UPDATE);
        this.onlineCount = builder.onlineCount;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public static class Builder {
        private Integer onlineCount;

        public Builder setOnlineCount(Integer onlineCount) {
            this.onlineCount = onlineCount;
            return this;
        }

        public OnlineUpdateResponse build() {
            return new OnlineUpdateResponse(this);
        }
    }
}
