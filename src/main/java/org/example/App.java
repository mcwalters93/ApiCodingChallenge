package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import org.example.bpdtsconnections.CallAPI;
import org.example.userinfo.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {

        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/api/usersWithin50Miles", (exchange -> {
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> userList = CallAPI.callAllUsers();
            exchange.sendResponseHeaders(200, 0);
            OutputStream output = exchange.getResponseBody();
            output.write(objectMapper.writeValueAsBytes(userList));
            output.flush();
            exchange.close();
        }));
        server.createContext("/api/usersListedLivingLondon", (httpExchange -> {
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> londonList = CallAPI.callLivingInLondon();
            httpExchange.sendResponseHeaders(200, 0);
            OutputStream output = httpExchange.getResponseBody();
            output.write(objectMapper.writeValueAsBytes(londonList));
            output.flush();
            httpExchange.close();
        }));
        server.setExecutor(null);
        server.start();

    }
}


