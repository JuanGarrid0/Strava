package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.*;
import java.time.LocalDateTime;

//TRAINING SESSION DTO
public class TrainingSessionDTO {
    private Long id;
    private String title;
    private Sport sport;
    private Double distance;
    private LocalDateTime start;
    private Integer durationMinutes;
    
    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public LocalDateTime getStart() {
        return start;
    }
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    public Integer getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }   
}
