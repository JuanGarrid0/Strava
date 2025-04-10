package es.deusto.sd.strava.dto;

// AcceptChallengeRequest.java
public class AcceptChallengeRequest {
    private Long userId;
    private Long challengeId;

    // HACEMOS GETTERS Y SETTERS
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getChallengeId() {
        return challengeId;
    }
    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

}
