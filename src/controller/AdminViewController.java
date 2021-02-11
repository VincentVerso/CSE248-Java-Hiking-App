package controller;

import app.SceneStateHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    private MenuItem exitMenuBtn;

    @FXML
    private MenuItem userViewMenuBtn;

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
    private Label trailsNotifyLbl;

    @FXML
    private TextField trailNameField;

    @FXML
    private TextField headAddressField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField elevationField;

    @FXML
    private ComboBox<String> difficultyChoice;
    private String difficultySelection;
    private ObservableList<String> difficultyList;

    @FXML
    private ComboBox<String> typeChoice;
    private String typeSelection;
    private ObservableList<String> typeList;

    @FXML
    private Button addTrailBtn;

    @FXML
    private Button deleteTrailBtn;

    @FXML
    private Button editTrailBtn;

    @FXML
    private TableView<Account> userAccTable;

    @FXML
    private TableColumn<Account, String> usernameCol;

    @FXML
    private TableColumn<Account, String> firstNameCol;

    @FXML
    private TableColumn<Account, String> lastNameCol;

    @FXML
    private TableColumn<Account, String> phoneNumCol;

    @FXML
    private TableColumn<Account, Boolean> isSuspendedCol;

    @FXML
    private Label userNotifyLbl;

    private ObservableList<Trail> trailsList;
    private ObservableList<Account> accountList;

    private SceneStateHandler sceneStateHandler;
    private UserDatabase userDatabase;
    private TrailsDatabase trailsDatabase;
    private Account loggedInUser;

    private Trail selectedTrail;
    private Account selectedAccount;

    public AdminViewController(SceneStateHandler sceneStateHandler, UserDatabase userDatabase, TrailsDatabase trailsDatabase, Account loggedInUser){
        this.sceneStateHandler = sceneStateHandler;
        this.userDatabase = userDatabase;
        this.trailsDatabase = trailsDatabase;
        this.loggedInUser = loggedInUser;
        difficultyList = FXCollections.observableArrayList("EASY", "MODERATE", "HARD");
        typeList = FXCollections.observableArrayList("LOOP", "OUT_AND_BACK", "POINT_TO_POINT");
        trailsList = FXCollections.observableArrayList();
        accountList = FXCollections.observableArrayList();
    }


    @FXML
    public void changeToUserView(ActionEvent event) {
        sceneStateHandler.changeSceneAfterLogin(loggedInUser.getUsername());
    }

    @FXML
    public void onAddTrailEvent(ActionEvent event) {
        String trailName = trailNameField.getText();
        String trailAdd = headAddressField.getText();
        String trailLength = lengthField.getText();
        String elevation = elevationField.getText();
        int length;
        int elevationGain;
        TrailDifficulty diff;
        TrailType type;

        if(trailName.isBlank() || trailAdd.isBlank() || trailLength.isBlank() || elevation.isBlank()){
            trailsNotifyLbl.setText("Do not leave fields blank!");
            return;
        }

        if(difficultySelection.isBlank() || typeSelection.isBlank()){
            trailsNotifyLbl.setText("Difficulty or trail Type not selected!");
            return;
        }

        if(!isInteger(trailLength) || !isInteger(elevation)){
            trailsNotifyLbl.setText("Trail length or elevation is not an integer!");
            return;
        }

        length = Integer.parseInt(trailLength);
        elevationGain = Integer.parseInt(elevation);

        Trail t = trailsDatabase.addTrail(trailName, trailAdd, length, elevationGain, TrailDifficulty.valueOf(difficultySelection), TrailType.valueOf(typeSelection));
        trailsList.add(t);
        trailsTable.getItems().add(t);

        trailsNotifyLbl.setText("Trail successfully added!");
    }

    @FXML
    public void onDeleteTrailEvent(ActionEvent event) {
        if(selectedTrail == null){
            trailsNotifyLbl.setText("No trail selected!");
            return;
        }
        trailsDatabase.removeTrail(selectedTrail.getTrailId());
        trailsList.remove(selectedTrail);
        DataSaver.saveTrailsData(trailsDatabase);
    }

    @FXML
    public void onEditTrailEvent(ActionEvent event) {
        if(selectedTrail == null){
            trailsNotifyLbl.setText("No trail selected!");
            return;
        }



    }

    @FXML
    public void onUserTableClickEvent(MouseEvent event) {
        selectedAccount = userAccTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void trailsTableClickEvent(MouseEvent event) {
        selectedTrail = trailsTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void onExitEvent(ActionEvent event) {
        System.exit(1);
    }

    private void setupTableView(){
        trailNameCol.setCellValueFactory(new PropertyValueFactory<>("trailName"));
        headAddressCol.setCellValueFactory(new PropertyValueFactory<>("trailHeadAddress"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        elevationCol.setCellValueFactory(new PropertyValueFactory<>("elevationGain"));
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("trailType"));

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        isSuspendedCol.setCellValueFactory(new PropertyValueFactory<>("isSuspended"));
    }

    private void loadTrails(){
        for(Trail trail : trailsDatabase.getTrailDatabase().values()){
            trailsList.add(trail);
        }
        trailsTable.getItems().addAll(trailsList);
    }

    private void loadUsers(){
        for(String key : userDatabase.getUserDatabase().keySet()){
            accountList.add(userDatabase.getAccount(key));
        }
        userAccTable.getItems().addAll(accountList);
    }

    private void setUpComboEvents(){
        difficultyChoice.setOnAction(e -> {
            difficultySelection = (String) difficultyChoice.getValue();
        });

        typeChoice.setOnAction(e -> {
            typeSelection = (String) typeChoice.getValue();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChoice.setItems(typeList);
        difficultyChoice.setItems(difficultyList);
        setupTableView();
        loadTrails();
        setUpComboEvents();
    }

    private static boolean isInteger(String str){
        try{
            int i = Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Not an integer. Can't Parse Int.");
        }
        return false;
    }
}
