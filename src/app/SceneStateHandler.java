package app;

import controller.LoginViewController;
import controller.SignupViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataSaver;
import model.TrailsDatabase;
import model.UserDatabase;

import java.io.IOException;

public class SceneStateHandler {

    private FXMLLoader loader;
    private Scene loginScene;
    private Scene signupScene;
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

        loginViewController = new LoginViewController(this, userDatabase);
        signupViewController = new SignupViewController(this, userDatabase);

        try{
            loader = new FXMLLoader(getClass().getResource("view/LoginView.fxml"));
            loader.setController(loginViewController);
            loginParent = loader.load(); //Set the loginParent to the FXML that was loaded.
        }catch (IOException e){
            e.printStackTrace();
        }

        loginScene = new Scene(loginParent, 1280, 720);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Hiking App");
        primaryStage.show();

    }

    //TODO: Implement method. Instead of checking if the user is admin or not, just load 'User View'. If admin they can change from that scene
    public void changeSceneAfterLogin(){

    }

    //Loads the signup scene
    public void changeSceneSignup(){
        try {
            loader = new FXMLLoader(getClass().getResource("view/SignupView.fxml"));
            loader.setController(signupViewController);
            signupParent = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        //Setup the scene and stage, show it.
        signupScene = new Scene(signupParent, 1280, 720);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Hiking App");
        primaryStage.show();
    }

    //Loads data if it exists
    private void loadData(){
        //Load data, if not file instantiate the classes.
        userDatabase = DataSaver.loadUserData();
        if(userDatabase == null){
            userDatabase = new UserDatabase();
        }
        trailsDatabase = DataSaver.loadTrailsData();
        if(trailsDatabase == null){
            trailsDatabase = new TrailsDatabase();
        }
    }

}
