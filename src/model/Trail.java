package model;

import java.io.Serializable;

public class Trail implements Serializable, Comparable<Trail> {
    private static final long serialVersionUID = 4871767531869058049L;

    private String trailName;
    private String trailHeadAddress;
    private int length; //in miles
    private int elevationGain; //in feet
    private TrailDifficulty difficulty;
    private TrailType trailType;
    private int trailId;

    public Trail(String trailName, String trailHeadAddress, int length, int elevationGain, TrailDifficulty trailDifficulty, TrailType trailType, int trailId){
        this.trailName = trailName;
        this.trailHeadAddress = trailHeadAddress;
        this.length = length;
        this.elevationGain = elevationGain;
        this.difficulty = trailDifficulty;
        this.trailType = trailType;
        this.trailId = trailId; //Trail Id can only be set when a new trail in instantiated.
    }

    public void setTrailName(String name){
        this.trailName = name;
    }

    public String getTrailName(){
        return trailName;
    }

    public String getTrailHeadAddress() {
        return trailHeadAddress;
    }

    public void setTrailHeadAddress(String trailHeadAddress) {
        this.trailHeadAddress = trailHeadAddress;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getElevationGain() {
        return elevationGain;
    }

    public void setElevationGain(int elevationGain) {
        this.elevationGain = elevationGain;
    }

    public TrailDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(TrailDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public TrailType getTrailType() {
        return trailType;
    }

    public void setTrailType(TrailType trailType) {
        this.trailType = trailType;
    }

    public int getTrailId(){
        return this.trailId;
    }

    public String toString(){
        return trailName + " Length: " + length + " Difficulty: " + difficulty.toString();
    }

    @Override
    public int compareTo(Trail o) {
        if(this.trailName.compareTo(o.getTrailName()) > 0){
            return 1;
        }else if(this.trailName.compareTo(o.getTrailName()) < 0){
            return -1;
        }
        return 0;
    }
}
