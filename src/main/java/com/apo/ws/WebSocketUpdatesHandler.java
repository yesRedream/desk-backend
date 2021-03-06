package com.apo.ws;

import com.apo.response.OnlineUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.logging.Logger;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 06/06/2017.
 */
public class WebSocketUpdatesHandler extends TextWebSocketHandler{
    private static final Logger LOGGER = Logger.getLogger(WebSocketUpdatesHandler.class.getName());

    @Autowired
    private WebSocketSessionManager socketSessionManager;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        LOGGER.info("Connection established");
        socketSessionManager.addSession(session);
        broadcastOnline();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        LOGGER.info("Connection closed");
        socketSessionManager.removeSession(session);
        broadcastOnline();
    }

    private void broadcastOnline() {
        socketSessionManager.broadcast(new OnlineUpdateResponse.Builder()
                .setOnlineCount(socketSessionManager.getSessionsCount())
                .build());
    }

}
