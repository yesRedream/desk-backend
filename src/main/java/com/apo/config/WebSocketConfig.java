package com.apo.config;

import com.apo.ws.WebSocketSessionManager;
import com.apo.ws.WebSocketUpdatesHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 06/06/2017.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getWsController(), "/updates").setAllowedOrigins("*");
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebSocketUpdatesHandler getWsController()  {
        return new WebSocketUpdatesHandler();
    }

    @Bean
    public WebSocketSessionManager getWsSessionManager() {
        return new WebSocketSessionManager();
    }

}
