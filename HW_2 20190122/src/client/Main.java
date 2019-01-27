package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.ClientHandler;
import server.Server;

public class Main extends Application {

    public static Boolean clo = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String auth = null;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("прошло 15 секунд");
                System.out.println("темп сейчас = " + ClientHandler.temp);
                if (!ClientHandler.temp){
                    clo = true;
                }
            }
        }).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
