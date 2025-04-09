package es.deusto.sd.strava.entity;

import java.util.Date;

public class ChallengeAcceptance {
    private Long idChallengeAcceptance;
    private Date acceptanceDate;
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
    
    public double calculateProgress(){
        double progress=0.0;
        return progress;
    }
}
