package model;

public class Grupo {

    //Atributos
    private int idGrupo;
    private String diaSemana;
    private String turno;
    private String responsable;

    //Constructores

    public Grupo() {}

    public Grupo(int idGrupo, String diaSemana, String turno, String responsable) {
        this.idGrupo = idGrupo;
        this.diaSemana = diaSemana;
        this.turno = turno;
        this.responsable = responsable;
    }

    //Getters y Setters

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "idGrupo=" + idGrupo +
                ", diaSemana='" + diaSemana + '\'' +
                ", turno='" + turno + '\'' +
                ", responsable='" + responsable + '\'' +
                '}';
    }
}
