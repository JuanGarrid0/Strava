package es.deusto.sd.strava.dto;

public class LoginRequest {
    private String email;
    private String password;
    private boolean authFromExternal;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAuthFromExternal() { return authFromExternal; }
    public void setAuthFromExternal(boolean authFromExternal) { this.authFromExternal = authFromExternal; }
}








