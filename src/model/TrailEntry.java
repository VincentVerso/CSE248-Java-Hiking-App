package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

//This class is used for the history.
//It will hold info such as, date and time started, date and time finished,
//the trail that was completed.
public class TrailEntry implements Serializable {

    private static final long serialVersionUID = -7978166323068502140L;
    private Trail trail; //The trail that was completed for this history entry.
    private String trailName;
    private LocalDate dateCompleted;
    private LocalDateTime dateTimeStarted;
    private LocalDateTime dateTimeCompleted;
    private String timeElapsed;

    public TrailEntry(Trail trail){
        this.trail = trail;
        trailName = trail.getTrailName();
        this.dateTimeStarted = LocalDateTime.now();
        timeElapsed = "";
    }

    public void completeTrail(){
        dateCompleted = LocalDate.now();
        dateTimeCompleted = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(dateTimeCompleted, dateTimeStarted);
        long minutes = ChronoUnit.MINUTES.between(dateTimeCompleted, dateTimeStarted);
        long seconds = ChronoUnit.SECONDS.between(dateTimeCompleted, dateTimeStarted);
        timeElapsed = String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);
    }

    public Trail getTrail(){
        return trail;
    }

    public String getTimeElapsed(){
        return timeElapsed;
    }

}
