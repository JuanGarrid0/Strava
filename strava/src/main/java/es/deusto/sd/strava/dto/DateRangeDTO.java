package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//TRAINING SESSION DTO
public class DateRangeDTO {
    @NotNull
    private LocalDate from;
    @NotNull
    private LocalDate to;

    //getters and setters
    public LocalDate getFrom() {
        return from;
    }
    public void setFrom(LocalDate from) {
        this.from = from;
    }
    public LocalDate getTo() {
        return to;
    }
    public void setTo(LocalDate to) {
        this.to = to;
    }

    
}
