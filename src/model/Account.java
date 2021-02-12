package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.TreeMap;

public class Account implements Serializable, Comparable<Account> {

    private static final long serialVersionUID = -8002487482721421078L;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String password;
    private String profileImage; //Store the file path for the img
    private boolean isSuspended;

    private TreeMap<LocalDate, LinkedList<TrailEntry>> trailHistory;

    //The trail the user is currently doing.
    //A trail is only added to history once its completed.
    //The start date and time is set in the trail entry on instantiation.
    //Once the currentTrail is completed, a method is called in the trail entry object
    //to compute the time elapsed.
    private TrailEntry currentTrail;

    public Account(String firstName, String lastName, String username, String phoneNumber, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.profileImage = "/src/default.png"; //Default image location
        this.isSuspended = false;
        trailHistory = new TreeMap<>();
        trailHistory.put(LocalDate.now(), new LinkedList<TrailEntry>());
    }

    public boolean isCurrentlyTakingTrail(){
        if(currentTrail != null){
            return true;
        }
        return false;
    }

    public void completeCurrentTrail(){
        //Make sure there is a current trail
        if(isCurrentlyTakingTrail()){
            //If the date(when trail was completed) already exists, add the current trail
            //and set currentTrail to null.
            if(trailHistory.containsKey(LocalDate.now())){
                currentTrail.completeTrail(); //Set the trail entry to completed
                trailHistory.get(LocalDate.now()).add(currentTrail);
                currentTrail = null;
            }else{
                //If the date does not exist in the history add it and instantiate new LinkedList
                //and add trail to it.
                currentTrail.completeTrail(); //Set the trail entry to completed
                trailHistory.put(LocalDate.now(), new LinkedList<TrailEntry>());
                trailHistory.get(LocalDate.now()).add(currentTrail);
                currentTrail = null;
            }
        }
    }

    public void setCurrentTrail(TrailEntry trailEntry){
        this.currentTrail = trailEntry;
    }

    public TrailEntry getCurrentTrail(){
        return currentTrail;
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

    public void setPassword(String password){
        this.password = password;
    }

    public void setProfileImage(String imagePath){
        this.profileImage = imagePath;
    }

    public boolean getIsSuspended(){
        return isSuspended;
    }

    public void setSuspended(boolean suspended){
        this.isSuspended = suspended;
    }

    public TreeMap<LocalDate, LinkedList<TrailEntry>> getTrailHistory() {
        return trailHistory;
    }

    public boolean isHistoryEmpty(){
        //Retrun true if empty
        if(trailHistory.size() < 1){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Account o) {
        if(this.username.compareTo(o.getUsername()) > 0){
            return 1;
        }else if(this.username.compareTo(o.getUsername()) < 0){
            return -1;
        }
        return 0;
    }
}
