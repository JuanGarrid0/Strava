package es.deusto.sd.strava.entity;

import java.util.Date;
import java.time.*;


public class Sesion {
    private long idTrainingSesion;
    private String title;
    private Sport sport;
    private double distance;
    private Date startDate;
    private LocalDateTime startTime;
    private int duration;
    public Sesion(long idTrainingSesion, String title, Sport sport, double distance, Date startDate,
            LocalDateTime startTime, int duration) {
        this.idTrainingSesion = idTrainingSesion;
        this.title = title;
        this.sport = sport;
        this.distance = distance;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
    }
    public long getIdTrainingSesion() {
        return idTrainingSesion;
    }
    public void setIdTrainingSesion(long idTrainingSesion) {
        this.idTrainingSesion = idTrainingSesion;
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
