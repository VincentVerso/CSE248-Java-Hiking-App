package controller;

import app.SceneStateHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import model.*;

public class UserViewController {

    private SceneStateHandler sceneStateHandler;
    private Account loggedInUser;
    private UserDatabase userDatabase;
    private TrailsDatabase trailsDatabase;

    @FXML
    private MenuItem editAccMenuBtn;

    @FXML
    private MenuItem exitMenuBtn;

    @FXML
    private MenuItem adminMenuBtn;


    @FXML
    private TableColumn<Trail, String> trailNameCol;

    @FXML
    private TableColumn<Trail, String> headAddressCol;

    @FXML
    private TableColumn<Trail, Integer> lengthCol;

    @FXML
    private TableColumn<Trail, Integer> elevationCol;

    @FXML
    private TableColumn<Trail, TrailDifficulty> difficultyCol;

    @FXML
    private TableColumn<Trail, TrailType> typeCol;

    public UserViewController(SceneStateHandler sceneStateHandler, Account loggedInUser, UserDatabase userDatabase, TrailsDatabase trailsDatabase){
        this.sceneStateHandler = sceneStateHandler;
        this.loggedInUser = loggedInUser;
        this.trailsDatabase = trailsDatabase;

        //We only want admins to have access to other users.
        if(loggedInUser instanceof AdminAccount){
            this.userDatabase = userDatabase;
        }
    }

    @FXML
    public void changeAdminViewEvent(ActionEvent event) {

    }

    @FXML
    public void editAccountEvent(ActionEvent event) {

    }

    @FXML
    public void exitEvent(ActionEvent event) {

    }

}
