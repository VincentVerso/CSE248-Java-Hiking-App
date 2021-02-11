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
    private TreeSet<Trail> trails;
    private int idCounter = 0; //Each time a trail is added this id will first increase by 1

    public TrailsDatabase(){
        trailDatabase = new HashMap<String, Trail>(50000, .5f);
        trails = new TreeSet<Trail>();
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

    public void addTrail(Trail trail){
        idCounter++; //increase id by 1
        trailDatabase.put(String.valueOf(idCounter), trail);
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
