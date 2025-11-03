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
        var listaAnimal = List.of(
                new Animal(1, "Elena", "Perro", 20, "En refugio", LocalDate.of(2025, 10, 28)),
                new Animal(2, "Puskas","Gato",   6, "Adoptado",   LocalDate.of(2024,  6, 11)),
                new Animal(3, "Rafa",  "Perro", 12, "En refugio", LocalDate.of(2023, 11, 16)),
                new Animal(4, "Mika",  "Gato",   3, "Adoptado",   LocalDate.of(2021,  4,  5))
        );

        //Insert
        /*for(Animal pet: listaAnimal){
            protectoraH.insertAnimal(pet);
            System.out.println("Animal insertado: "+pet.getIdAnimal()+" "+pet.getNombreAnimal()+" "+pet.getEdad());
        }*/

        //Delete
//        Animal animal1=listaAnimal.get(2);
//        protectoraH.deleteAnimal(animal1);
//        System.out.println("Datos del animal eliminado: "+animal1.getIdAnimal()+" "+animal1.getNombreAnimal()+" "+animal1.getTipo());

//        //Update
//        Animal animal2=listaAnimal.get(0);
//        animal2.setEstado("Adoptado");
//        protectoraH.updateAnimalEstado(animal2);
//        System.out.println("El estado del animal ha sido actualizado: "+animal2.getIdAnimal()+" "+animal2.getNombreAnimal()+" "+animal2.getEstado());

            //Edad media de los animales adoptados
           System.out.println(protectoraH.getEdadMediaAdopcion());



    }
}
