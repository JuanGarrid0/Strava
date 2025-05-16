package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

//CHALLENGE DTO
public class ChallengeCreateDTO {
    @NotBlank
    private String name;

    @NotNull
    private Sport sport;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Positive
    private Double objectiveValue;

    // getters and setters
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

    
}
