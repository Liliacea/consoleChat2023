package consoleChat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    BufferedReader in;
    PrintWriter out;
    Socket socket;
    String login;

    public Client() {
        String ip = "127.0.0.1";
        try {
            socket = new Socket(ip, Constants.PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Введите ваше имя: ");
            out.println(new Scanner(System.in).nextLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Resender resender = new Resender();
        resender.start();
        String textMessage = " ";
        while (!(resender.message.equals(Constants.EXIT))){

        }

    }





    public class Resender extends Thread {
        private boolean stoped;
        String message;
        public void setStoped() {
            this.stoped = true;
        }

        @Override
        public void run() {
            while (!stoped){
                try {
                    message = in.readLine();
                } catch (IOException e) {
                    System.out.println("разрыв соединения");
                    throw new RuntimeException(e);
                }
            }
    }
    }
}
