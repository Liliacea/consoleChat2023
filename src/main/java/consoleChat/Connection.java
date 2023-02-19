package consoleChat;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private String login;
    private Thread thread;
    ConnectionListener listener;

    public Connection(Socket socket, ConnectionListener listener) throws IOException {
        this.socket = socket;
        this.listener = listener;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.login = login;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    login = in.readLine();
                    listener.onConnectionReady(Connection.this);
                    while (!thread.isInterrupted()) {
                        listener.RecieveString(Connection.this, in.readLine());
                    }
                }
                    catch (IOException e) {

                    throw new RuntimeException(e);
                }
            }
        });


    }
    public void sendMessage(String message){
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            disconnect();
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
