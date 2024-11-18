package com.example.zologin.config.webSocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class MultiUserWebSocketServer extends WebSocketServer {
    private ConcurrentHashMap<String, WebSocket> clients = new ConcurrentHashMap<>();

    public MultiUserWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String userId = handshake.getFieldValue("User-ID"); // Lấy thông tin User-ID từ header
        if (userId != null) {
            clients.put(userId, conn);
            System.out.println("User connected: " + userId);
        } else {
            conn.close(4001, "User-ID required");
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clients.values().remove(conn); // Xóa kết nối khỏi danh sách
        System.out.println("Connection closed: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message received: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started!");
    }

    public void sendToUser(String userId, String message) {
        WebSocket conn = clients.get(userId);
        if (conn != null) {
            conn.send(message);
        }
    }
}
