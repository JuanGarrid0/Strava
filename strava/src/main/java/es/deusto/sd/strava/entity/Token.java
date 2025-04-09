package es.deusto.sd.strava.entity;

import java.util.Date;

public class Token {
    private long idToken;
    private String valueToken;
    private Date creationDate;
    public Token(long idToken, String valueToken, Date creationDate) {
        this.idToken = idToken;
        this.valueToken = valueToken;
        this.creationDate = creationDate;
    }
    public long getIdToken() {
        return idToken;
    }
    public void setIdToken(long idToken) {
        this.idToken = idToken;
    }
    public String getValueToken() {
        return valueToken;
    }
    public void setValueToken(String valueToken) {
        this.valueToken = valueToken;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
