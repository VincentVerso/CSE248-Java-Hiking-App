package model;

import java.io.Serializable;

public class Trail implements Serializable {
    private static final long serialVersionUID = 4871767531869058049L;

    //TODO: Implement Comparable interface

    private String trailName;
    private String trailHeadAddress;
    private double length; //in miles
    private int elevationGain; //in feet
    private TrailDifficulty difficulty;
    private TrailType trailType;

    public Trail(String trailName, String trailHeadAddress, double length, int elevationGain, TrailDifficulty trailDifficulty, TrailType trailType){
        this.trailName = trailName;
        this.trailHeadAddress = trailHeadAddress;
        this.length = length;
        this.elevationGain = elevationGain;
        this.difficulty = trailDifficulty;
        this.trailType = trailType;
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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
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
}
