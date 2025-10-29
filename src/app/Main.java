package app;
import model.Animal;
import mysql.ProtectoraLoginHandleDB;
import util.DBConnectionProtectora;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProtectoraLoginHandleDB protectoraH=new ProtectoraLoginHandleDB();
        Animal animal=new Animal();

        var listaAnimal = List.of(
                new Animal(0, "Elena", "Perro", 20, "En refugio", LocalDate.of(2025, 10, 28)),
                new Animal(0, "Puskas","Gato",   6, "Adoptado",   LocalDate.of(2024,  6, 11)),
                new Animal(0, "Rafa",  "Perro", 12, "En refugio", LocalDate.of(2023, 11, 16)),
                new Animal(0, "Mika",  "Gato",   3, "Adoptado",   LocalDate.of(2021,  4,  5))
        );


        //Comprobación insert en la BD
        for(Animal pet: listaAnimal){
            protectoraH.insertAnimal(pet);
            System.out.println("Animal introducido en la protectora" +pet.getIdAnimal() + pet.getNombreAnimal());
        }

        //Comprobación update con el primer elemento de la lista
        Animal primerAnimal= listaAnimal.get(0);
        primerAnimal.setEstado("Adoptado");
        protectoraH.updateAnimal(primerAnimal);
        System.out.println("AnimaL actualizado: "+primerAnimal.getIdAnimal()+" "+primerAnimal.getNombreAnimal()+" "+primerAnimal.getEstado());



        //Eliminación del tercer animal de la BD
        Animal animaltercero=listaAnimal.get(2);
        protectoraH.deleteAnimal(animaltercero);
        System.out.println("Se ha eliminado el "+animaltercero.getIdAnimal());





    }
}
