package es.deusto.sd.strava.dto;

import java.util.Date;

import es.deusto.sd.strava.entity.*;

// ChallengeRequest.java
public class CreationChallengeRequest {
    private String name;
    private Date startDate;
    private Date endDate;
    private double objectiveValue;
    private Sport sport;
   
    // HACEMOS GETTERS Y SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public double getObjectiveValue() {
        return objectiveValue;
    }
    public void setObjectiveValue(double objectiveValue) {
        this.objectiveValue = objectiveValue;
    }
    public Sport getSport() {
        return sport;
    }
    public void setSport(Sport sport) {
        this.sport = sport;
    }

}
