package controller;

import app.SceneStateHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    private SceneStateHandler sceneStateHandler;
    private UserDatabase userDatabase;
    private TrailsDatabase trailsDatabase;
    private Account loggedInUser;

    public AdminViewController(SceneStateHandler sceneStateHandler, UserDatabase userDatabase, TrailsDatabase trailsDatabase, Account loggedInUser){
        this.sceneStateHandler = sceneStateHandler;
        this.userDatabase = userDatabase;
        this.trailsDatabase = trailsDatabase;
        this.loggedInUser = loggedInUser;
        difficultyList = FXCollections.observableArrayList("EASY", "MODERATE", "HARD");
        typeList = FXCollections.observableArrayList("LOOP", "OUT_AND_BACK", "POINT_TO_POINT");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChoice.setItems(typeList);
        difficultyChoice.setItems(difficultyList);
    }
}
