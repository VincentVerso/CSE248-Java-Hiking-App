package controller;

import app.SceneStateHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Account;
import model.UserDatabase;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Label notifyLbl;

    private SceneStateHandler sceneStateHandler;
    private UserDatabase userDatabase;

    private Account loggedInUser;

    public LoginViewController(SceneStateHandler sceneStateHandler, UserDatabase userDatabase){
        this.sceneStateHandler = sceneStateHandler;
        this.userDatabase = userDatabase;
    }

    @FXML
    public void loginEvent(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(isFieldsEmpty()){
            notifyLbl.setText("Username or password is blank!");
        }else{
            if(userDatabase.contains(username)){
                if(userDatabase.getAccount(username).getPassword().equals(password)){
                    sceneStateHandler.changeSceneAfterLogin(username);
                }else{
                    notifyLbl.setText("Incorrect password!");
                }
            }else{
                notifyLbl.setText("Username does not exits!");
            }
        }

    }

    @FXML
    public void signupEvent(ActionEvent event) {
        sceneStateHandler.changeSceneSignup();
    }

    private boolean isFieldsEmpty(){
        if(usernameField.getText().isBlank() || passwordField.getText().isBlank()){
            return true;
        }else {
            return false;
        }
    }

}
