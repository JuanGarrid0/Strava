package es.deusto.sd.strava.entity;




public class Sport {
    public enum SportType {
        CICLISMO,
        RUNNING
    }

    private SportType tipo;

    public Sport(SportType tipo) {
        this.tipo = tipo;
    }

    public SportType getTipo() {
        return tipo;
    }

    public void setTipo(SportType tipo) {
        this.tipo = tipo;
    }

}
