package es.deusto.sd.meta;

import java.time.LocalDate;

public class UserMeta {
    private final String email;
    private final String name;
    private final LocalDate birthDate;

    public UserMeta(String email, String name, LocalDate birthDate) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }
    // getters

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    
}
