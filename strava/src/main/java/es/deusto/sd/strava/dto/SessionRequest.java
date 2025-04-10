package es.deusto.sd.strava.dto;

import java.time.LocalDateTime;
import java.util.Date;

import es.deusto.sd.strava.entity.*;



public class SessionRequest {
    private Long userId;
    private String title;
    private Sport sport;
    private double distance;
    private Date startDate;
    private LocalDateTime startTime;
    private int duration;

    //HACEMOS GETTERS Y SETTERS

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Sport getSport() {
        return sport;
    }
    public void setSport(Sport sport) {
        this.sport = sport;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

}