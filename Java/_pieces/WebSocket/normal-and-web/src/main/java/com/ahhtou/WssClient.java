package com.ahhtou;


import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WssClient {


    static {
        System.out.println("~~~~~");
    }

    public WssClient() {
        System.out.println("websocket");
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session);
        System.out.println("OnOpen...");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("OnMessage" + message);
    }

}
