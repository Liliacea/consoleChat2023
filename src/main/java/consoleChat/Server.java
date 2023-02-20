package consoleChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements ConnectionListener {
    private ArrayList <Connection> connections;
    ServerSocket serverSocket;

    public Server() {

        try {
            connections = new ArrayList<>();
            serverSocket = new ServerSocket(Constants.PORT);

            new Connection(serverSocket.accept(),this);
        } catch (IOException e) {
            System.out.println("ошибка подключения к серверу");
            e.printStackTrace();
        }
        try {


            new Connection(serverSocket.accept(),this);
        } catch (IOException e) {
            System.out.println("ошибка при создании подключения");
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionReady(Connection connection) {
        connections.add(connection);
        String message = "подключение пользователя " + connection.getLogin();
        System.out.println(message);
        sendMessage(message );

    }

    @Override
    public void onDisconnection(Connection connection) {
        connections.remove(connection);
        String message = "отключение пользователя " + connection.getLogin();
        System.out.println(message);
        sendMessage(message );

    }

    @Override
    public void RecieveString(Connection connection, String message) {
        sendMessage(message);
    }

    private void sendMessage(String message) {
        for (Connection item:connections) {
            item.sendMessage(message);

        }
    }
}
