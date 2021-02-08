package controller;

import app.SceneStateHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.TrailsDatabase;
import model.UserAccount;
import model.UserDatabase;

public class SignupViewController {

    private SceneStateHandler sceneStateHandler;
    private UserDatabase userDatabase;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField conPasswordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumField;

    @FXML
    private Button signupBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label notifyLbl;

    public SignupViewController(SceneStateHandler sceneStateHandler, UserDatabase userDatabase){
        this.sceneStateHandler = sceneStateHandler;
        this.userDatabase = userDatabase;
    }

    @FXML
    public void backEvent(ActionEvent event) {
        //When back button is clicked, return to login scene.
        sceneStateHandler.backToLoginScene();
    }

    @FXML
    public void signupEvent(ActionEvent event) {
        //Check to see if fields are blank, if so notify and return.
        if(isSignupFieldsEmpty()){
            notifyLbl.setText("Please do not leave any fields blank!");
            return;
        }

        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumField.getText();
        String password = passwordField.getText();
        String conPassword = conPasswordField.getText();

        if(password.equals(conPassword)){ //Make sure passwords are the same
            if(!userDatabase.contains(username)){ //Make sure account does not exist.
                userDatabase.addUserAccount(new UserAccount(firstName, lastName, username, phoneNumber, phoneNumber));
                notifyLbl.setText("Signup successful! Click Back to login.");
            }else{
                notifyLbl.setText("Username already exits!");
            }
        }else {
            notifyLbl.setText("Passwords do not match!");
        }
    }

    //Checks for blank fields
    private boolean isSignupFieldsEmpty(){
        if(usernameField.getText().equals("") || passwordField.getText().equals("") || conPasswordField.getText().equals("") || firstNameField.getText().equals("")|| lastNameField.getText().equals("") || phoneNumField.getText().isBlank()){
            return true;
        }else {
            return false;
        }
    }
}
