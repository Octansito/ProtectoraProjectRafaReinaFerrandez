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


//
//        //Comprobación update con el primer elemento de la lista
//        Animal primerAnimal= listaAnimal.get(0);
//        primerAnimal.setEstado("Adoptado");
//        protectoraH.updateAnimalEstado(primerAnimal);
//        System.out.println("AnimaL actualizado: "+primerAnimal.getIdAnimal()+" "+primerAnimal.getNombreAnimal()+" "+primerAnimal.getEstado());
//
//
//        //Eliminación del tercer animal de la BD
//        Animal animaltercero=listaAnimal.get(2);
//        protectoraH.deleteAnimal(animaltercero);
//        System.out.println("Se ha eliminado el "+animaltercero.getIdAnimal());

          System.out.println(protectoraH.getTotalByEstado("Adoptado"));
          System.out.println(protectoraH.getAdopcionesByTipo("Perro"));

    }
}
