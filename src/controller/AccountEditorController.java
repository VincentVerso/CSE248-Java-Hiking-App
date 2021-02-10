package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.Account;
import model.DataSaver;
import model.UserDatabase;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountEditorController implements Initializable {

    @FXML
    private ImageView profilePic;

    @FXML
    private Label firstNameLbl;

    @FXML
    private Label lastNameLbl;

    @FXML
    private Label usernameLbl;

    @FXML
    private Label phoneNumberLbl;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField phoneNumField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField conPasswordField;

    @FXML
    private Button updateProfileBtn;

    @FXML
    private Label notifyLbl;

    private Account account;
    private UserDatabase userDatabase;

    public AccountEditorController(Account account, UserDatabase userDatabase){
        this.account = account;
        this.userDatabase = userDatabase;
    }

    @FXML
    public void profilePicClickEvent(MouseEvent event) {
        FileChooser chooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        chooser.getExtensionFilters().addAll(extFilterPNG, extFilterJPG);

        //Show open file dialog
        File file = chooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            profilePic.setImage(image);
            account.setProfileImage(file.toURI().toString());
            DataSaver.saveUserDatabase(userDatabase);
        }
    }

    @FXML
    public void updateProfileEvent(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumField.getText();
        String password = passwordField.getText();
        String conPassword = conPasswordField.getText();

        if(!firstName.isBlank()){
            account.setFirstName(firstName);
        }

        if(!lastName.isBlank()){
            account.setLastName(lastName);
        }


        if(!phoneNumber.isBlank()){
            account.setPhoneNumber(phoneNumber);
        }

        if(!password.isBlank() && !conPassword.isBlank()){
            if(password.equals(conPassword)){
                account.setPassword(password);
            }else{
                notifyLbl.setText("Passwords do not match!");
            }
        }
        updateLabels();
        DataSaver.saveUserDatabase(userDatabase);
    }

    private void updateLabels(){
        firstNameLbl.setText("First Name: " + account.getFirstName());
        lastNameLbl.setText("Last Name: " + account.getLastName());
        usernameLbl.setText("Username: " + account.getUsername());
        phoneNumberLbl.setText("Phone Number: " + account.getPhoneNumber());
        profilePic.setImage(new Image("resources/default.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateLabels();
    }
}
