package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.main.ClientUI;
import ui.main.ClientUINodesManager;

public class ControllerLogIn implements ControlledNode {

    @FXML private TextField mTextFieldUsername;
    @FXML private PasswordField mTextFieldPassword;

    private ClientUINodesManager mClientUINodesManager;

    @Override
    public void init() {

    }

    @Override
    public void setNodesManager(ClientUINodesManager clientUINodesManager) {
        mClientUINodesManager = clientUINodesManager;
    }

    @FXML
    public void handleButtonLogIn() {
        // todo
    }

    @FXML
    public void handleButtonSignUp() {
        mClientUINodesManager.setNode(ClientUI.LAYOUT_SIGNUP, ClientUINodesManager.SlideAnimationDirection.GO_LEFT);
    }

    @FXML
    public void handleButtonAlternateLogIn() {
        mClientUINodesManager.setNode(ClientUI.LAYOUT_ALTERNATE_LOGIN, ClientUINodesManager.SlideAnimationDirection.GO_LEFT);
    }
}
