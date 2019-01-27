package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        AuthService.connect();
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(7771);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
//                clients.add(new ClientHandler(this, socket));
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String msg, String sendler) {
        for (ClientHandler o : clients) {
            o.sendMsg(sendler + " пишет: " + msg);
            o.writeLog(msg);
        }
    }

    public void goodby(String msg, String sendler) {
        for (ClientHandler o : clients) {
            o.sendMsg(sendler + " " + msg);
            o.writeLog(msg);
        }
    }

    public void sendToNick (String msg, String nickName, String sendler){
        for (ClientHandler o : clients){
            String tmp = o.getClient(o);
            if (tmp.equals(sendler)) {
                o.sendMsg(sendler + " пишет для " + nickName + " : " + msg);
                o.writeLog(msg);
            }
            if (tmp.equals(nickName)) {
                o.sendMsg(sendler + " пишет для " + nickName + " : " + msg);
                o.writeLog(msg);
            }
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }


}
