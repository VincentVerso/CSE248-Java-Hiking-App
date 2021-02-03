package model;

import java.io.Serializable;

//This class is used for the history.
//It will hold info such as, date and time started, date and time finished,
//the trail that was completed.
public class TrailEntry implements Serializable {

    private static final long serialVersionUID = -7978166323068502140L;
    private Trail trail; //The trail that was completed for this history entry.


    public TrailEntry(){

    }

}
