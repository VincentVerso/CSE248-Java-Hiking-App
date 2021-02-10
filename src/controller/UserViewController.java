package controller;

import app.SceneStateHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

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

    @FXML
    private Label trailNameLbl;

    @FXML
    private Label trailAddressLbl;

    @FXML
    private Label trailLengthLbl;

    @FXML
    private Label elevationLbl;

    @FXML
    private Label difficultyLbl;

    @FXML
    private Label trailTypeLbl;

    @FXML
    private Label notifyLbl;

    private String search;
    private ObservableList<Trail> trailSearchList;
    private AccountEditorController accountEditorController;

    public UserViewController(SceneStateHandler sceneStateHandler, Account loggedInUser, UserDatabase userDatabase, TrailsDatabase trailsDatabase){
        this.sceneStateHandler = sceneStateHandler;
        this.loggedInUser = loggedInUser;
        this.trailsDatabase = trailsDatabase;
        accountEditorController = new AccountEditorController(loggedInUser, userDatabase);

        //We only want admins to have access to other users.
        if(loggedInUser instanceof AdminAccount){
            this.userDatabase = userDatabase;
        }
    }

    @FXML
    public void changeAdminViewEvent(ActionEvent event) {
        if(loggedInUser instanceof AdminAccount) {
            sceneStateHandler.changeToAdminView(loggedInUser.getUsername());
        }
    }

    @FXML
    public void editAccountEvent(ActionEvent event) {
        Parent root;
        FXMLLoader loader;
        try{
            loader = new FXMLLoader(getClass().getResource("/view/AccountEditorView.fxml"));
            loader.setController(accountEditorController);
            root = loader.load();

            Stage userStage = new Stage();
            userStage.setTitle("Profile Editor");
            userStage.setScene(new Scene(root, 600, 400));
            userStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onCompleteTrailEvent(ActionEvent event) {
        if(loggedInUser.isCurrentlyTakingTrail()){
            loggedInUser.completeCurrentTrail();
            loadHistory();
            notifyLbl.setText("Trail completed!");
        }else{
            notifyLbl.setText("You are not currently taking a trail!");
        }
    }

    @FXML
    public void onSearchEvent(KeyEvent event) {

        search = searchField.getText();
        if(!search.isBlank()){
            Set<Trail> trails = trailsDatabase.getTrailDatabase().values().stream()
                    .filter(trail -> trail.getTrailName().contains(search) || trail.getTrailHeadAddress().contains(search) ||
                            trail.getTrailType().toString().toLowerCase().contains(search) ||
                            trail.getDifficulty().toString().toLowerCase().contains(search) ||
                            trail.getDifficulty().toString().toLowerCase().contains(search) ||
                            String.valueOf(trail.getLength()).contains(search)).collect(Collectors.toSet());
            trailSearchList = FXCollections.observableArrayList(trails);
            if(!trails.isEmpty()) {
                trailsTable.getItems().clear();
                loadTrailIntoTable();
            }
        }else{
            //Clear the list and table.
            if(!trailsTable.getItems().isEmpty()) {
                trailSearchList.clear();
                trailsTable.getItems().clear();
            }
        }
    }

    @FXML
    public void onTakeTrailEvent(ActionEvent event) {
        if(loggedInUser.isCurrentlyTakingTrail()){
            notifyLbl.setText("You can take another trail after completing current trail.");
            return;
        }

        selectedTrail = trailsTable.getSelectionModel().getSelectedItem();
        if(selectedTrail == null){
            notifyLbl.setText("No trail selected!");
            return;
        }
        TrailEntry entry = new TrailEntry(selectedTrail);
        loggedInUser.setCurrentTrail(entry);
        updateTrailLabels();
        notifyLbl.setText("Successfully taken trail!");
    }

    @FXML
    public void exitEvent(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void trailsTableClickEvent(MouseEvent event) {
        selectedTrail = trailsTable.getSelectionModel().getSelectedItem();
        if(selectedTrail == null) {
            System.out.println("Trail selected. " + selectedTrail.toString());
        }
    }

    //Sets up each column with its respective field for a trial.
    private void setupTableView(){
        trailNameCol.setCellValueFactory(new PropertyValueFactory<>("trailName"));
        headAddressCol.setCellValueFactory(new PropertyValueFactory<>("trailHeadAddress"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        elevationCol.setCellValueFactory(new PropertyValueFactory<>("elevationGain"));
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("trailType"));

        hisTrailNameCol.setCellValueFactory(new PropertyValueFactory<>("trailName"));
        hisDateCompletedCol.setCellValueFactory(new PropertyValueFactory<>("dateTimeCompleted"));
        timeElapsedCol.setCellValueFactory(new PropertyValueFactory<>("timeElapsed"));
    }

    //Gets all the keys from the database(HashMap) and returns LinkedList<String>
    //Then iterates through LinkedList and gets each trail and inserts into table.
    private void loadTrailIntoTable(){
        //LinkedList<String> trailsData = trailsDatabase.getTrailNames();
        //If the trails search list is not empty, iterate and to table.
        if(!trailSearchList.isEmpty()){
            //Iterate through the list and add each trail to the table.
            trailSearchList.forEach((trail) ->{
              if(!trailsTable.getItems().contains(trail)){
                  trailsTable.getItems().add(trail);
              }
            });
        }
    }

    private void loadHistory(){
        Collection<LinkedList<TrailEntry>> history = loggedInUser.getTrailHistory().values();

        //This is necessary to not have duplicate values after updating history when a trail is completed.
        //This will also keep things in proper order.
        if(!historyTable.getItems().isEmpty()){
            historyTable.getItems().clear();
        }

        //Display history in trials history table.
        for(LinkedList<TrailEntry> list : history){
            if(!list.isEmpty()){ //Ensure there are values in the list
                for(TrailEntry trail : list){
                    historyTable.getItems().add(trail);
                }
            }
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
        //loadTrailIntoTable();
        checkForCurrentTrail();
    }

    //This method will check if the user is currently taking a trail.
    //If so update the labels.
    private void checkForCurrentTrail(){
        if(loggedInUser.getCurrentTrail() != null){
            updateTrailLabels();
        }
    }

    //Updates the labels based on current trail the user is taking.
    private void updateTrailLabels(){
        trailNameLbl.setText("Trail Name: " + loggedInUser.getCurrentTrail().getTrail().getTrailName());
        trailAddressLbl.setText("Trail Address: " + loggedInUser.getCurrentTrail().getTrail().getTrailHeadAddress());
        trailLengthLbl.setText("Trail Length: " + loggedInUser.getCurrentTrail().getTrail().getLength());
        elevationLbl.setText("Elevation Gain: " + loggedInUser.getCurrentTrail().getTrail().getElevationGain());
        difficultyLbl.setText("Trail Difficulty: " + loggedInUser.getCurrentTrail().getTrail().getDifficulty().toString());
        trailTypeLbl.setText("Trail Type: " + loggedInUser.getCurrentTrail().getTrail().getTrailType().toString());
    }

    private void resetLabels(){
        trailNameLbl.setText("Trail Name: ");
        trailAddressLbl.setText("Trail Address: ");
        trailLengthLbl.setText("Trail Length: ");
        elevationLbl.setText("Elevation Gain: ");
        difficultyLbl.setText("Trail Difficulty: ");
        trailTypeLbl.setText("Trail Type: ");
    }
}
