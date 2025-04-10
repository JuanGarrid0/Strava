package es.deusto.sd.strava.entity;

import java.util.Date;

public class User {
    private Long idUser;
    private String email;
    private Date birthdate;
    private double weight;
    private int height;
    private double maxHearRate;
    private double normalHeartRate;
    private String name;
    private String password;

    // Ejemplo de contador interno (no recomendable para producción)
    private static long contador = 1;

    public User() {
    }

    public User(String email, Date birthdate, double weight, int height, double maxHearRate, double normalHeartRate,
                String name, String password) {
        this.email = email;
        this.birthdate = birthdate;
        this.weight = weight;
        this.height = height;
        this.maxHearRate = maxHearRate;
        this.normalHeartRate = normalHeartRate;
        this.name = name;
        this.password = password;
        this.idUser = contador++;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMaxHearRate() {
        return maxHearRate;
    }

    public void setMaxHearRate(double maxHearRate) {
        this.maxHearRate = maxHearRate;
    }

    public double getNormalHeartRate() {
        return normalHeartRate;
    }

    public void setNormalHeartRate(double normalHeartRate) {
        this.normalHeartRate = normalHeartRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
