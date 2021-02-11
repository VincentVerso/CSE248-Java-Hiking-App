package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

//Class for storing all the trails and their respective data.
public class TrailsDatabase implements Serializable {

    private static final long serialVersionUID = -2223849863310362697L;
    //The string(key) will be the trail name;
    private HashMap<String, Trail> trailDatabase;
    private int idCounter = 0; //Each time a trail is added this id will first increase by 1

    public TrailsDatabase(){
        trailDatabase = new HashMap<String, Trail>(50000, .5f);
        addTrail("Test Trail", "5 Test Ave., NY", 5, 2, TrailDifficulty.EASY, TrailType.LOOP);
        addTrail("A Trail", "52 Test Ave., NY", 8, 10, TrailDifficulty.MODERATE, TrailType.LOOP);
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
    public boolean contains(int trailId){
        if(trailDatabase.containsKey(trailId)){
            return true;
        }
        return false;
    }

    public Trail addTrail(String trailName, String trailHeadAddress, int length, int elevationGain, TrailDifficulty trailDifficulty, TrailType trailType){
        idCounter++; //increase id by 1
        Trail t = new Trail(trailName, trailHeadAddress, length, elevationGain, trailDifficulty, trailType, idCounter);
        trailDatabase.put(String.valueOf(idCounter), t);
        return t;
    }

    public boolean removeTrail(int trailId){
        if(trailDatabase.containsKey(trailId)){
            trailDatabase.remove(trailId);
            return true;
        }
        return false;
    }

    public Trail getTrail(int trailId){
        if(trailDatabase.containsKey(trailId)) {
            return trailDatabase.get(trailId);
        }
        return null;
    }

}
