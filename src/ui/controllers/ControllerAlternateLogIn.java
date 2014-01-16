package ui.controllers;

import Utils.Console;
import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.main.ClientUI;
import ui.main.ClientUINodesManager;

public class ControllerAlternateLogIn implements ControlledNode {

    @FXML private TextField mTextFieldID;
    @FXML private TextField mTextFieldPort;
    @FXML private TextField mTextFieldIPAddress;

    private ClientUINodesManager mClientUINodesManager;
    private Client mClient;

    @Override
    public void init() {
        mClient = mClientUINodesManager.getClient();
    }

    @Override
    public void setNodesManager(ClientUINodesManager clientUINodesManager) {
        mClientUINodesManager = clientUINodesManager;
    }

    @FXML
    public void handleButtonLogIn() {
        Integer id = null;
        Integer port = null;
        try {
            id = Integer.parseInt(mTextFieldID.getText());
            port = Integer.parseInt(mTextFieldPort.getText());
        } catch (NumberFormatException e) {
            Console.error(e.getMessage());
            return;
        }
        if (id == null || port == null) return;
        String ipAddress = mTextFieldIPAddress.getText();
        mClient.setID(id);
        mClient.connectToServer(port, ipAddress);
    }

    @FXML
    public void handleButtonCancel() {
        mClientUINodesManager.setNode(ClientUI.LAYOUT_LOGIN, ClientUINodesManager.SlideAnimationDirection.GO_RIGHT);
    }
}
