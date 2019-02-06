package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static Logger logger = Logger.getLogger(Server.class.getName());

    private Vector<ClientHandler> clients;

    public Server() {
        AuthService.connect();
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(7771);
            logger.log(Level.INFO, "Сервер запущен!");
            //System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                logger.log(Level.INFO,"Клиент подключился");
                //System.out.println("Клиент подключился");
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

    public void broadcastMsg(String msg, String sendler) throws IOException {
        logger.log(Level.INFO, "Клиент прислал сообщение");
        for (ClientHandler o : clients) {
            o.sendMsg(sendler + " пишет: " + msg);
            String nickToWrite = o.getClient(o);
            o.writeLog(nickToWrite,sendler + " пишет: " + msg);
        }
    }

    public void goodby(String msg, String sendler) throws IOException{
        logger.log(Level.INFO, "Клиент отключился");
        for (ClientHandler o : clients) {
            o.sendMsg(sendler + " " + msg);
            String nickToWrite = o.getClient(o);
            o.writeLog(nickToWrite,sendler + " " + msg);
        }
    }

    public void sendToNick (String msg, String nickName, String sendler) throws IOException{
        logger.log(Level.INFO, "Клиент прслал приватное сообщение");
        for (ClientHandler o : clients){
            String tmp = o.getClient(o);
            if (tmp.equals(sendler)) {
                o.sendMsg(sendler + " пишет для " + nickName + " : " + msg);
                o.writeLog(sendler,sendler + " пишет для " + nickName + " : " + msg);
            }
            if (tmp.equals(nickName)) {
                o.sendMsg(sendler + " пишет для " + nickName + " : " + msg);
                o.writeLog(nickName,sendler + " пишет для " + nickName + " : " + msg);
            }
        }
    }

    public void loadingToNick (String msg, String nickName){
        for (ClientHandler o : clients){
            String tmp = o.getClient(o);
            if (tmp.equals(nickName)){
                o.sendMsg(msg);
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
