package es.deusto.sd.strava.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;
    
    @Column (name = "birth_date", nullable = false)
    private LocalDate birthdate;

    private Double weight;
    private Integer height;

    @Column (name = "max_heart_rate")
    private Integer maxHearRate;

    @Column(name = "rest_heart_rate")
    private Integer restHeartRate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingSession> sessions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeAcceptance> acceptances;

    //GETTERS Y SETTERS, NO HAY CONSTRUCTORES

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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

    public Integer getMaxHearRate() {
        return maxHearRate;
    }

    public void setMaxHearRate(Integer maxHearRate) {
        this.maxHearRate = maxHearRate;
    }

    public Integer getRestHeartRate() {
        return restHeartRate;
    }

    public void setRestHeartRate(Integer restHeartRate) {
        this.restHeartRate = restHeartRate;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<TrainingSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TrainingSession> sessions) {
        this.sessions = sessions;
    }

    public List<ChallengeAcceptance> getAcceptances() {
        return acceptances;
    }

    public void setAcceptances(List<ChallengeAcceptance> acceptances) {
        this.acceptances = acceptances;
    }

}