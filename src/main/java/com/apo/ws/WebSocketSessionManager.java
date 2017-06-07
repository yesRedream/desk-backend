package com.apo.ws;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 06/06/2017.
 */
public class WebSocketSessionManager {
    private List<WebSocketSession> socketSessions = new ArrayList<>();

    public boolean addSession(WebSocketSession session) {
        return socketSessions.add(session);
    }

    public boolean removeSession(WebSocketSession session) {
        return  socketSessions.remove(session);
    }

    public void sendToAll(String string) {
        socketSessions.forEach(session -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(string));
                    System.out.println("Send to session " + session.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public List<WebSocketSession> getSocketSessions() {
        return socketSessions;
    }

    public void setSocketSessions(List<WebSocketSession> socketSessions) {
        this.socketSessions = socketSessions;
    }
}
