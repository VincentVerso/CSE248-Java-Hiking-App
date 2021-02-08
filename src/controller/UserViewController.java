package controller;

import app.SceneStateHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    private SceneStateHandler sceneStateHandler;
    private Account loggedInUser;
    private UserDatabase userDatabase;
    private TrailsDatabase trailsDatabase;
    private Trail selectedTrail;

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

    @FXML
    private TextField searchField;

    @FXML
    private Button takeTrailBtn;

    @FXML
    private Button completeTrailBtn;

    @FXML
    private TableView<TrailEntry> historyTable;

    @FXML
    private TableColumn<TrailEntry, String> hisTrailNameCol;

    @FXML
    private TableColumn<TrailEntry, LocalDate> hisDateCompletedCol;

    @FXML
    private TableColumn<TrailEntry, String> timeElapsedCol;


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
    public void onCompleteTralilEvent(ActionEvent event) {

    }

    @FXML
    public void onSearchEvent(KeyEvent event) {

    }

    @FXML
    public void onTakeTralilEvent(ActionEvent event) {

    }

    @FXML
    public void exitEvent(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void trailsTableClickEvent(MouseEvent event) {
        selectedTrail = trailsTable.getSelectionModel().getSelectedItem();
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

        if(loggedInUser instanceof AdminAccount){
            //If the user is an Admin, enable this button.
            adminMenuBtn.setDisable(false);
        }else {
            adminMenuBtn.setDisable(true);
        }
        setupTableView();
        loadTrailIntoTable();
    }
}
