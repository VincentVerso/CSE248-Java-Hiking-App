package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.TreeMap;

public class Account implements Serializable {

    private static final long serialVersionUID = -8002487482721421078L;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String password;
    private String profileImage; //Store the file path for the img

    private TreeMap<LocalDate, LinkedList<TrailEntry>> trailHistory;

    //The trail the user is currently doing.
    private TrailEntry currentTrail;

    public Account(String firstName, String lastName, String username, String phoneNumber, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.profileImage = "src/default.png"; //Default image location
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage(){
        return profileImage; //Returns the path of this users profile image
    }

    public String getPassword(){
        return password;
    }

    public void setProfileImage(String imagePath){
        this.profileImage = imagePath;
    }

    public TreeMap<LocalDate, LinkedList<TrailEntry>> getTrailHistory() {
        return trailHistory;
    }
}
