package server;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ClientHandler {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Server server;
    private String nick;

    private HashMap<ClientHandler, String> listClients = new HashMap<>();

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //цикл авторизации
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickname(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(ClientHandler.this);
                                    listClients.put(ClientHandler.this,nick);

                                    //тут надо читать логфайл с именем nick или создавать если его нет
                                    File log = new File(nick + ".txt");
                                    if (!log.exists()) {
                                        FileOutputStream fileOutputStream = new FileOutputStream(nick + ".txt");
                                        fileOutputStream.close();
                                    } else {
                                     FileInputStream fileInputStream = new FileInputStream(nick + ".txt");
                                        Vector<InputStream> loadingLog = new Vector<>();
                                        loadingLog.add(fileInputStream);
                                        SequenceInputStream sequenceInputStream = new SequenceInputStream(loadingLog.elements());
                                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                        int b;
                                        while ((b = sequenceInputStream.read()) != -1){
                                            outputStream.write(b);
                                        }
                                        byte[] barr = outputStream.toByteArray();
                                        String s = new String (barr);
                                        System.out.println(s);
                                     fileInputStream.close();
                                    }
                                    break;
                                } else {
                                    sendMsg("Неверный логин/пароль");
                                }
                            }
                        }
                        //рабочий цикл
                        while (true) {
                            String str = in.readUTF();
                            String sender = getClient(ClientHandler.this);
                            if (str.equals("/end")) {
                                server.goodby(" вышел из чата", sender);
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.startsWith("/w")) {
                                String[] parsingNick = str.split(" ", 3);
                                String toNick = parsingNick[1];
                                String newStr = parsingNick[2];
                                server.sendToNick(newStr,toNick, sender);
                            } else {
                                server.broadcastMsg(str, sender);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClient (ClientHandler qwe){
        return listClients.get(qwe);
    }

    public void writeLog(String msg){
        //открыть файл и записать туда сообщение

    }

}
