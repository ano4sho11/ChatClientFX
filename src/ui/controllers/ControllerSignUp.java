package ui.controllers;

import ui.main.ClientUI;
import ui.main.ClientUINodesManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerSignUp implements ControlledNode {

    @FXML private TextField mTextFieldFirstName;
    @FXML private TextField mTextFieldLastName;
    @FXML private TextField mTextFieldUsername;
    @FXML private PasswordField mPasswordFieldPassword;
    @FXML private PasswordField mPasswordFieldPasswordAgain;
    @FXML private TextField mTextFieldEmail;
    @FXML private TextField mTextFieldEmailAgain;
    @FXML private Button mButtonSubmit;
    @FXML private Button mButtonCancel;

    private ClientUINodesManager mClientUINodesManager;

    @Override
    public void init() {

    }

    @Override
    public void setNodesManager(ClientUINodesManager clientUINodesManager) {
        mClientUINodesManager = clientUINodesManager;
    }

    @FXML
    public void handleButtonSignUp() {

    }

    @FXML
    public void handleButtonCancel() {
        mClientUINodesManager.setNode(ClientUI.LAYOUT_LOGIN, ClientUINodesManager.SlideAnimationDirection.GO_RIGHT);
    }

}
