package es.deusto.sd.strava.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

//TRAINING SESSION DTO
public class DateRangeDTO {
    @NotNull
    private LocalDate from;
    @NotNull
    private LocalDate to;
    @NotNull
    private String name;



    //getters and setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
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
