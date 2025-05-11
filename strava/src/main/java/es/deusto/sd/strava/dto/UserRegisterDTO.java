package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//USER DTO
public class UserRegisterDTO {
    @Email @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate birthDate;

    @PositiveOrZero
    private Double weight;

    @PositiveOrZero
    private Integer height;

    @PositiveOrZero
    private Integer maxHeartRate;

    @PositiveOrZero
    private Integer restHeartRate;

    @NotNull
    private AuthProvider provider;

    // getters and setters
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

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }
    
}
