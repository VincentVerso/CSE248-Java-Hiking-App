package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTrailController implements Initializable {

    @FXML
    private Label trailNameLbl;

    @FXML
    private Label addressLbl;

    @FXML
    private Label lengthLbl;

    @FXML
    private Label elevationLbl;

    @FXML
    private Label difficultyLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private Label notifyLbl;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField elevationField;

    @FXML
    private ComboBox<String> difficultyCombo;
    private String difficultySelection;
    private ObservableList<String> difficultyList;

    @FXML
    private ComboBox<String> typeCombo;
    private String typeSelection;
    private ObservableList<String> typeList;

    @FXML
    private Button updateBtn;

    private Trail trail; //Trail to edit
    private TrailsDatabase trailsDatabase;

    public EditTrailController(Trail trail, TrailsDatabase trailsDatabase){
        this.trail = trail;
        this.trailsDatabase = trailsDatabase;
        difficultyList = FXCollections.observableArrayList("EASY", "MODERATE", "HARD");
        typeList = FXCollections.observableArrayList("LOOP", "OUT_AND_BACK", "POINT_TO_POINT");
    }

    @FXML
    public void onUpdateTrailEvent(ActionEvent event) {
        String trailName = nameField.getText();
        String trailAdd = addressField.getText();
        String trailLength = lengthField.getText();
        String elevation = elevationField.getText();
        int length;
        int elevationGain;
        TrailDifficulty diff;
        TrailType type;

        if(!trailName.isBlank()){
            trail.setTrailName(trailName);
        }

        if(!trailAdd.isBlank()){
            trail.setTrailHeadAddress(trailAdd);
        }

        if(!trailLength.isBlank() && isInteger(trailLength)){
            trail.setLength(Integer.parseInt(trailLength));
        }else{
            notifyLbl.setText("Trail length must be an integer!");
        }

        if(!elevation.isBlank() && isInteger(elevation)){
            trail.setElevationGain(Integer.parseInt(elevation));
        }else{
            notifyLbl.setText("Elevation must be an integer!");
        }

        if(!difficultySelection.isBlank()){
            trail.setDifficulty(TrailDifficulty.valueOf(difficultySelection));
        }

        if(!typeSelection.isBlank()){
            trail.setTrailType(TrailType.valueOf(typeSelection));
        }

        DataSaver.saveTrailsData(trailsDatabase);

    }

    private void setUpComboEvents(){
        difficultyCombo.setOnAction(e -> {
            difficultySelection = (String) difficultyCombo.getValue();
        });

        typeCombo.setOnAction(e -> {
            typeSelection = (String) typeCombo.getValue();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
