package model;

import java.time.LocalDate;

public class Adopcion {

    //Atributos
    private int idAdopcion;
    private int idAnimal;
    private String nombreAdoptante;
    private String telefono;
    private LocalDate fechaAdopcion;
    private String direccion;

    //Constructores
    public Adopcion() {}

    public Adopcion(int idAdopcion, String direccion, LocalDate fechaAdopcion, int idAnimal, String telefono, String nombreAdoptante) {
        this.idAdopcion = idAdopcion;
        this.direccion = direccion;
        this.fechaAdopcion = fechaAdopcion;
        this.idAnimal = idAnimal;
        this.telefono = telefono;
        this.nombreAdoptante = nombreAdoptante;
    }

    //Getters y Setters

    public int getIdAdopcion() {
        return idAdopcion;
    }

    public void setIdAdopcion(int idAdopcion) {
        this.idAdopcion = idAdopcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreAdoptante() {
        return nombreAdoptante;
    }

    public void setNombreAdoptante(String nombreAdoptante) {
        this.nombreAdoptante = nombreAdoptante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(LocalDate fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    @Override
    public String toString() {
        return "Adopcion{" +
                "idAdopcion=" + idAdopcion +
                ", idAnimal=" + idAnimal +
                ", nombreAdoptante='" + nombreAdoptante + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaAdopcion=" + fechaAdopcion +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
