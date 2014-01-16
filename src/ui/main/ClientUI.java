package ui.main;

import client.Client;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientUI extends Application {

    public static final String LAYOUT_LOGIN = "LAYOUT_LOGIN";
    public static final String LAYOUT_LOGIN_PATH = "../layouts/LogIn.fxml";

    public static final String LAYOUT_ALTERNATE_LOGIN = "LAYOUT_ALTERNATE_LOGIN";
    public static final String LAYOUT_ALTERNATE_LOGIN_PATH = "../layouts/AlternateLogIn.fxml";

    public static final String LAYOUT_CHAT_UI = "LAYOUT_CHAT_UI";
    public static final String LAYOUT_CHAT_UI_PATH = "../layouts/ChatUI.fxml";

    public static final String LAYOUT_SIGNUP = "LAYOUT_SIGNUP";
    public static final String LAYOUT_SIGNUP_PATH = "../layouts/SignUp.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Client");

        final Client client = new Client();

        ClientUINodesManager clientUINodesManager = new ClientUINodesManager(primaryStage);
        clientUINodesManager.setClient(client);
        clientUINodesManager.loadNode(LAYOUT_LOGIN, LAYOUT_LOGIN_PATH);
        clientUINodesManager.loadNode(LAYOUT_SIGNUP, LAYOUT_SIGNUP_PATH);
        clientUINodesManager.loadNode(LAYOUT_ALTERNATE_LOGIN, LAYOUT_ALTERNATE_LOGIN_PATH);
        clientUINodesManager.loadNode(LAYOUT_CHAT_UI, LAYOUT_CHAT_UI_PATH);
        clientUINodesManager.setNode(LAYOUT_LOGIN, ClientUINodesManager.SlideAnimationDirection.GO_LEFT);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(clientUINodesManager);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client.shutDown();
                    }
                }).start();
            }
        });
    }

    public static void main(String... args) {
        Application.launch(args);
    }
}
