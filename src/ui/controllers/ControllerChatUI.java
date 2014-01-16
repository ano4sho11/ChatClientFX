package ui.controllers;

import client.Client;
import ui.main.ClientUINodesManager;
import javafx.stage.Stage;

public class ControllerChatUI implements ControlledNode {

    private ClientUINodesManager mClientUINodesManager;
    private Stage mStage;
    private Client mClient;

    @Override
    public void init() {
        mStage = new Stage();
        mStage.setTitle("Chat UI");
        mClient = mClientUINodesManager.getClient();

    }

    @Override
    public void setNodesManager(ClientUINodesManager clientUINodesManager) {
        mClientUINodesManager = clientUINodesManager;
    }
}
