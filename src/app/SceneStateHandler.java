package app;

import controller.LoginViewController;
import controller.SignupViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TrailsDatabase;
import model.UserDatabase;

import java.io.IOException;

public class SceneStateHandler {

    private FXMLLoader loader;
    private Scene scene;
    private Stage primaryStage;

    private UserDatabase userDatabase;
    private TrailsDatabase trailsDatabase;

    //Login signup parents
    private Parent loginParent;
    private Parent signupParent;

    private Parent userViewParent;
    private Parent adminViewParent;

    //The controllers for each respective scene.
    private LoginViewController loginViewController;
    private SignupViewController signupViewController;

    public SceneStateHandler(Stage primaryStage){
        loadData(); //Load the data(if available) before doing anything else.
        this.primaryStage = primaryStage;

        loginViewController = new LoginViewController();
        signupViewController = new SignupViewController();

        try{
            loader = new FXMLLoader(getClass().getResource("view/LoginView.fxml"));
            loader.setController(loginViewController);
            loginParent = loader.load(); //Set the loginParent to the FXML that was loaded.
        }catch (IOException e){
            e.printStackTrace();
        }

        scene = new Scene(loginParent, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hiking App");
        primaryStage.show();

    }

    //TODO: Implement method. Instead of checking if the user is admin or not, just load 'User View'. If admin they can change from that scene
    public void changeSceneAfterLogin(){

    }



    //Loads data if it exists
    private void loadData(){

    }

}
