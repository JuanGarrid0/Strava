package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//USER DTO
public class UserLoginDTO {
    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private AuthProvider provider;

    // getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }    
}
