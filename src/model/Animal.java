package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Animal {

    //Atributos
    private int idAnimal;
    private String nombreAnimal;
    private String tipo;
    private int edad;
    private String estado;
    private LocalDate fechaIngreso;


    //Constructores
    public Animal() {}

    public Animal(int idAnimal, String nombreAnimal,String tipo, int edad, String estado, LocalDate fechaIngreso) {
        this.idAnimal = idAnimal;
        this.nombreAnimal = nombreAnimal;
        this.tipo = tipo;
        this.edad = edad;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
    }

    //Getters y Setters
    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }

    public void setNombreAnimal(String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", nombreAnimal='" + nombreAnimal + '\'' +
                ", tipo='" + tipo + '\'' +
                ", edad=" + edad +
                ", estado='" + estado + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
