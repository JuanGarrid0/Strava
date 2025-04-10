package es.deusto.sd.strava.entity;

import java.util.Date;
import java.time.LocalDateTime;

public class TrainingSession {
    private long idTrainingSesion;
    private String title;
    private Sport sport;
    private double distance;
    private Date startDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Date endDate;
    private int duration;
    private User user;

    public TrainingSession() {
    }

    public TrainingSession(long idTrainingSesion, String title, Sport sport, double distance, Date startDate,
                           LocalDateTime startTime, LocalDateTime endTime, Date endDate, int duration, User user) {
        this.idTrainingSesion = idTrainingSesion;
        this.title = title;
        this.sport = sport;
        this.distance = distance;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.duration = duration;
        this.user = user;
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
