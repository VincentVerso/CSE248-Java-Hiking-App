package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

//Class for storing all the trails and their respective data.
public class TrailsDatabase implements Serializable {

    private static final long serialVersionUID = -2223849863310362697L;
    //The string(key) will be the trail name;
    private HashMap<String, Trail> trailDatabase;

    public TrailsDatabase(){
        trailDatabase = new HashMap<String, Trail>(50000, .5f);
        addTrail(new Trail("Test Trail", "5 Test Ave., NY", 5, 2, TrailDifficulty.EASY, TrailType.LOOP));
        addTrail(new Trail("A Trail", "52 Test Ave., NY", 8, 10, TrailDifficulty.MODERATE, TrailType.LOOP));
        DataSaver.saveTrailsData(this);
    }

    public HashMap<String, Trail> getTrailDatabase(){
        return trailDatabase;
    }

    public LinkedList<String> getTrailNames(){
        LinkedList<String> names = new LinkedList<String>(trailDatabase.keySet());
        return names;
    }

    //Checks to see if the trail exists or not.
    public boolean contains(String trailName){
        if(trailDatabase.containsKey(trailName)){
            return true;
        }
        return false;
    }

    public boolean addTrail(Trail trail){
        if(!trailDatabase.containsKey(trail.getTrailName())){
            trailDatabase.put(trail.getTrailName(), trail);
            return true;
        }
        return false;
    }

    public boolean removeTrail(String trailName){
        if(trailDatabase.containsKey(trailName)){
            trailDatabase.remove(trailName);
            return true;
        }
        return false;
    }

    public Trail getTrail(String trailName){
        if(trailDatabase.containsKey(trailName)) {
            return trailDatabase.get(trailName);
        }
        return null;
    }

}
