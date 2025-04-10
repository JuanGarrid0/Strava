package es.deusto.sd.strava.entity;

import java.util.Date;

public class ChallengeAcceptance {

    private Long idChallengeAcceptance;
    private Date acceptanceDate;

    // Como no usas BBDD, puedes modelar relaciones como simples referencias
    private User user;
    private Challenge challenge;

    public ChallengeAcceptance() {
    }

    public ChallengeAcceptance(Long idChallengeAcceptance, Date acceptanceDate) {
        this.idChallengeAcceptance = idChallengeAcceptance;
        this.acceptanceDate = acceptanceDate;
    }

    public Long getIdChallengeAcceptance() {
        return idChallengeAcceptance;
    }

    public void setIdChallengeAcceptance(Long idChallengeAcceptance) {
        this.idChallengeAcceptance = idChallengeAcceptance;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public double calculateProgress(){
        // La lógica real la puedes centralizar luego en un servicio
        double progress = 0.0;
        return progress;
    }
}
