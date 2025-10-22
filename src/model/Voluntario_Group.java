package model;

public class Voluntario_Group {
    //Atributos
    private String dniVoluntario;
    private int idGrupo;

    //Constructores
    public Voluntario_Group() {}

    public Voluntario_Group(String dniVoluntario, int idGrupo) {
        this.dniVoluntario = dniVoluntario;
        this.idGrupo = idGrupo;
    }

    //Getters y Setters

    public String getDniVoluntario() {
        return dniVoluntario;
    }

    public void setDniVoluntario(String dniVoluntario) {
        this.dniVoluntario = dniVoluntario;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public String toString() {
        return "Voluntario_Group{" +
                "dniVoluntario='" + dniVoluntario + '\'' +
                ", idGrupo=" + idGrupo +
                '}';
    }
}
