package model;

import java.io.Serializable;

public class Trail implements Serializable {
    private static final long serialVersionUID = 4871767531869058049L;

    //TODO: Add fields and methods

    private String trailName;

    public Trail(String trailName){
        this.trailName = trailName;
    }

    public String getTrailName(){
        return trailName;
    }

}
