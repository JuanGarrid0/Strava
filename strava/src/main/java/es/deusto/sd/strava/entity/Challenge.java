package es.deusto.sd.strava.entity;

import java.util.Date;

public class Challenge {
    private Long idChallenge;
    private String name;
    private Date startDate;
    private Date endDate;
    private Double objectiveValue;
    private Sport sport;
    public Challenge(Long idChallenge, String name, Date startDate, Date endDate, Double objectiveValue, Sport sport) {
        this.idChallenge = idChallenge;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.objectiveValue = objectiveValue;
        this.sport = sport;
    }
    public Long getIdChallenge() {
        return idChallenge;
    }
    public void setIdChallenge(Long idChallenge) {
        this.idChallenge = idChallenge;
    }
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
    public Double getObjectiveValue() {
        return objectiveValue;
    }
    public void setObjectiveValue(Double objectiveValue) {
        this.objectiveValue = objectiveValue;
    }
    public Sport getSport() {
        return sport;
    }
    public void setSport(Sport sport) {
        this.sport = sport;
    }
    public boolean isActive(){
        boolean activity=true;
        return activity;
    }

}
