package com.apo.ws;

import com.apo.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 06/06/2017.
 */
public class WebSocketSessionManager {
    private static final Logger LOGGER = Logger.getLogger(WebSocketSessionManager.class.getName());
    private List<WebSocketSession> socketSessions = new ArrayList<>();

    public boolean addSession(WebSocketSession session) {
        return socketSessions.add(session);
    }

    public boolean removeSession(WebSocketSession session) {
        return  socketSessions.remove(session);
    }

    public void broadcast(Response response) {
        socketSessions.forEach(session -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(response)));
                    LOGGER.info("Send to session " + session.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getSessionsCount() {
        return socketSessions.size();
    }

    public List<WebSocketSession> getSocketSessions() {
        return socketSessions;
    }
}
