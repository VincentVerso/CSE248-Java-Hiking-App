package app;

import controller.AdminViewController;
import controller.LoginViewController;
import controller.SignupViewController;
import controller.UserViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import model.DataSaver;
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
    private UserViewController userViewController;
    private AdminViewController adminViewController;
    private Account loggedInUser;

    public SceneStateHandler(Stage primaryStage){
        loadData(); //Load the data(if available) before doing anything else.
        this.primaryStage = primaryStage;

        loginViewController = new LoginViewController(this, userDatabase);
        signupViewController = new SignupViewController(this, userDatabase);

        try{
            loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
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

    public void changeSceneAfterLogin(String username){
        loggedInUser = userDatabase.getAccount(username);
        userViewController = new UserViewController(this, loggedInUser, userDatabase, trailsDatabase);
        try {
            loader = new FXMLLoader(getClass().getResource("/view/UserView.fxml"));
            loader.setController(userViewController);
            userViewParent = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        //Set the scenes root show it.
        scene.setRoot(userViewParent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hiking App");
        primaryStage.show();
    }

    public void changeToAdminView(String username){
        loggedInUser = userDatabase.getAccount(username);
        adminViewController = new AdminViewController(this, userDatabase, trailsDatabase, loggedInUser);

        try {
            loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));
            loader.setController(adminViewController);
            adminViewParent = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        //Set the scenes root show it.
        scene.setRoot(adminViewParent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hiking App");
        primaryStage.show();
    }

    //Loads the signup scene
    public void changeSceneSignup(){
        try {
            loader = new FXMLLoader(getClass().getResource("/view/SignupView.fxml"));
            loader.setController(signupViewController);
            signupParent = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        //Setup the scene and stage, show it.
        scene.setRoot(signupParent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hiking App");
        primaryStage.show();
    }

    public void backToLoginScene(){
        try{
            loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
            loader.setController(loginViewController);
            loginParent = loader.load(); //Set the loginParent to the FXML that was loaded.
        }catch (IOException e){
            e.printStackTrace();
        }

        scene.setRoot(loginParent);
        primaryStage.setScene(scene);
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
