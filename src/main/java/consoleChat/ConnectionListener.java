package consoleChat;

public interface ConnectionListener {
     void onConnectionReady(Connection connection);
     void onDisconnection (Connection connection);
     void RecieveString (Connection connection, String value);
}
