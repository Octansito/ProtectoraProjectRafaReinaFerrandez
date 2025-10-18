package model;

public class Voluntario {

    //Atributos
    private String dni;
    private String nombreVoluntario;
    private String telefono;
    private String rol;
    private int antiguedad;

    //Constructores

    public Voluntario() {}

    public Voluntario(String dni, String nombreVoluntario, String telefono, String rol, int antiguedad) {
        this.dni = dni;
        this.nombreVoluntario = nombreVoluntario;
        this.telefono = telefono;
        this.rol = rol;
        this.antiguedad = antiguedad;
    }

    //Getters y Setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreVoluntario() {
        return nombreVoluntario;
    }

    public void setNombreVoluntario(String nombreVoluntario) {
        this.nombreVoluntario = nombreVoluntario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Voluntario{" +
                "dni='" + dni + '\'' +
                ", nombreVoluntario='" + nombreVoluntario + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + rol + '\'' +
                ", antiguedad=" + antiguedad +
                '}';
    }
}
