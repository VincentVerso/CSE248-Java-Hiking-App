package controller;

import app.SceneStateHandler;
import model.*;

public class UserViewController {

    private SceneStateHandler sceneStateHandler;
    private Account loggedInUser;
    private UserDatabase userDatabase;
    private TrailsDatabase trailsDatabase;

    public UserViewController(SceneStateHandler sceneStateHandler, Account loggedInUser, UserDatabase userDatabase, TrailsDatabase trailsDatabase){
        this.sceneStateHandler = sceneStateHandler;
        this.loggedInUser = loggedInUser;
        this.trailsDatabase = trailsDatabase;

        //We only want admins to have access to other users.
        if(loggedInUser instanceof AdminAccount){
            this.userDatabase = userDatabase;
        }
    }

}
