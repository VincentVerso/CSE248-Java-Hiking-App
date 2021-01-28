package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.TreeMap;

public class Account {

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
}
