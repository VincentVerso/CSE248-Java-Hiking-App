package controller;

import app.SceneStateHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.UserDatabase;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Label notifyLbl;

    private SceneStateHandler sceneStateHandler;
    private UserDatabase userDatabase;

    public LoginViewController(SceneStateHandler sceneStateHandler, UserDatabase userDatabase){
        this.sceneStateHandler = sceneStateHandler;
        this.userDatabase = userDatabase;
    }

    @FXML
    public void loginEvent(ActionEvent event) {

    }

    @FXML
    public void signupEvent(ActionEvent event) {

    }

}
