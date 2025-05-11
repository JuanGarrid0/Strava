package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//CHALLENGE DTO
public class UserChallengeDTO {
    private Long id;
    private String name;
    private Sport sport;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double objectiveValue;
    private Double progressPercent;

    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Sport getSport() {
        return sport;
    }
    public void setSport(Sport sport) {
        this.sport = sport;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Double getObjectiveValue() {
        return objectiveValue;
    }
    public void setObjectiveValue(Double objectiveValue) {
        this.objectiveValue = objectiveValue;
    }
    public Double getProgressPercent() {
        return progressPercent;
    }
    public void setProgressPercent(Double progressPercent) {
        this.progressPercent = progressPercent;
    }

    
}
