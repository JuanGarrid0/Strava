package es.deusto.sd.strava.dto;

import java.time.LocalDateTime;

//USER DTO
public class TokenDTO {
    private String value;
    private LocalDateTime createdAt;
    
    // getters and setters
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }   
}
