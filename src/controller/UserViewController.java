package controller;

import app.SceneStateHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

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
    private TableView<Trail> trailsTable;

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
        System.exit(0);
    }

    //Sets up each column with its respective field for a trial.
    private void setupTableView(){
        trailNameCol.setCellValueFactory(new PropertyValueFactory<>("trailName"));
        headAddressCol.setCellValueFactory(new PropertyValueFactory<>("trailHeadAddress"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        elevationCol.setCellValueFactory(new PropertyValueFactory<>("elevationGain"));
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("trailType"));
    }

    //Gets all the keys from the database(HashMap) and returns LinkedList<String>
    //Then iterates through LinkedList and gets each trail and inserts into table.
    private void loadTrailIntoTable(){
        LinkedList<String> trailsData = trailsDatabase.getTrailNames();
        for(String trailKey: trailsData){
           trailsTable.getItems().add(trailsDatabase.getTrail(trailKey));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableView();
        loadTrailIntoTable();
    }
}
