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

        List<Animal> listaAnimal=new ArrayList<>();
        listaAnimal.add(new Animal(1,"Elena","Perro", 20, "En refugio", LocalDate.of(2025, 10, 28)));
        listaAnimal.add(new Animal(1,"Puskas","Gato", 6, "Adoptado", LocalDate.of(2024, 6, 11)));
        listaAnimal.add(new Animal(1,"Elena","Perro", 20, "En refugio", LocalDate.of(2023, 11, 16)));
        listaAnimal.add(new Animal(1,"Elena","Perro", 20, "Adoptado", LocalDate.of(2021, 4, 5)));


        for(Animal pets: listaAnimal){
            protectoraH.insertAnimal(animal);
            System.out.println("Animal introducido en la protectora");
        }

        protectoraH.deleteAnimal(listaAnimal.get(2));
        //protectoraH.updateAnimal(animal.getIdAnimal(1),animal.getNombreAnimal("Elena"), "Perro", 20, "Adoptado", LocalDate.of(2025,10,28)));




    }
}
