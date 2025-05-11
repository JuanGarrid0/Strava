package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//USER DTO
public class UserProfileDTO {
    private Long id;
    private String email;
    private String name;
    private LocalDate birthDate;
    private Double weight;
    private Integer height;
    private Integer maxHeartRate;
    private Integer restHeartRate;

    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getMaxHeartRate() {
        return maxHeartRate;
    }
    public void setMaxHeartRate(Integer maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }
    public Integer getRestHeartRate() {
        return restHeartRate;
    }
    public void setRestHeartRate(Integer restHeartRate) {
        this.restHeartRate = restHeartRate;
    }   
}
