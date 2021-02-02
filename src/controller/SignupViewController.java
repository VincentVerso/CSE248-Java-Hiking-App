package controller;

import app.SceneStateHandler;
import model.TrailsDatabase;
import model.UserDatabase;

public class SignupViewController {

    private SceneStateHandler sceneStateHandler;
    private UserDatabase userDatabase;

    public SignupViewController(SceneStateHandler sceneStateHandler, UserDatabase userDatabase){
        this.sceneStateHandler = sceneStateHandler;
        this.userDatabase = userDatabase;
    }
}
