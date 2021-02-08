package controller;

import app.SceneStateHandler;
import javafx.scene.Scene;
import model.Account;
import model.TrailsDatabase;
import model.UserDatabase;

public class AdminViewController {

    private SceneStateHandler sceneStateHandler;
    private UserDatabase userDatabase;
    private TrailsDatabase trailsDatabase;
    private Account loggedInUser;

    public AdminViewController(SceneStateHandler sceneStateHandler, UserDatabase userDatabase, TrailsDatabase trailsDatabase, Account loggedInUser){
        this.sceneStateHandler = sceneStateHandler;
        this.userDatabase = userDatabase;
        this.trailsDatabase = trailsDatabase;
        this.loggedInUser = loggedInUser;
    }

}
