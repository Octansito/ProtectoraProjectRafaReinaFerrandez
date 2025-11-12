package app;


import dto.AdopcionDTO;
import dto.MediaEdadDTO;
import model.Animal;
import mysql.ProtectoraLoginHandleDB;
import util.DBConnectionProtectora;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que contiene el menú de opciones para interactuar con la base de datos de la protectora
 */

        public class Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                ProtectoraLoginHandleDB protectoraHandler = new ProtectoraLoginHandleDB();

                int opcion =0;
                while (opcion != 7) {
                    System.out.println("--> MENÚ PRINCIPAL PROTECTORA <--");
                    System.out.println("1- Probar conexión con la base de datos");
                    System.out.println("2- Insertar animal");
                    System.out.println("3- Eliminar animal");
                    System.out.println("4- Actualizar datos del animal");
                    System.out.println("5- Obtener la media de edad de los tipos de animales adoptados ");
                    System.out.println("6- Consulta todas las adopciones por tipo de animal");
                    System.out.println("7- Salir");

                    System.out.println("Introduce la opción:");
                    opcion = Integer.parseInt(sc.nextLine());

                    switch (opcion) {
                        case 1 -> {
                            if (DBConnectionProtectora.getConnection() != null) {
                                System.out.println("Conexión correctamente establecida");
                            } else {
                                System.out.println("Error al establecer conexión con la red neuronal");
                            }
                        }

                        case 2 -> {
                            System.out.println(" Introduce los datos del nuevo animal que desea introducir");

                            String continuar="";

                            do{
                                System.out.println("Identificador del animal");
                                int id=Integer.parseInt(sc.nextLine());
                                System.out.println("Nombre del animal: ");
                                String nombre=sc.nextLine();
                                System.out.println("Especie (Perro/Gato): ");
                                String tipo= sc.nextLine();
                                System.out.println("Edad del animal (años): ");
                                int edad = Integer.parseInt(sc.nextLine());
                                System.out.println("Estado (En refugio / Adoptado)");
                                String estado= sc.nextLine();
                                System.out.println("Fecha de ingreso (YYYY-MM-DD): ");
                                LocalDate fechaIngreso = LocalDate.parse(sc.nextLine());


                                protectoraHandler.insertAnimal(new Animal(id, nombre, tipo, edad, estado, fechaIngreso));
                                System.out.println("Animal insertado correctamente");


                                // Preguntar si quiere introducir otro
                                System.out.println("¿Desea introducir otro animal? (s/n):");
                                continuar = sc.nextLine().trim().toLowerCase();
                            }while  (continuar.equals("s"));


                            System.out.println("Volviendo al menú principal");


                        }

                        case 3 -> {
                            // Mostrar lista antes de eliminar
                            List<Integer> idsDisponibles = protectoraHandler.mostrarAnimalesEnRefugio();

                            if (idsDisponibles.isEmpty()) {
                                System.out.println("No hay animales en refugio para eliminar.");
                                System.out.println("Volviendo al menú...");
                                break;
                            }

                            //Introducir cuál eliminar
                            System.out.println("Introduce el ID del animal que deseas eliminar:");
                            int idEliminar = Integer.parseInt(sc.nextLine());
                            if (!idsDisponibles.contains(idEliminar)) {
                                System.out.println("ID no válido. Solo puedes eliminar animales en refugio.");
                                break;
                            }else{
                                protectoraHandler.deleteAnimal(idEliminar);
                            }

                            // Mostramos de nuevo la lista tras eliminar
                            List<Integer> idsTotales= protectoraHandler.mostrarAnimalesEnRefugio();

                            if (idsTotales.isEmpty()) {
                                System.out.println("No quedan animales en la protectora.");
                            }
                        }

                        case 4 -> {
                            System.out.println("Mostrando lista de animales registrados en la base de datos de la protectora....");
                            List<Animal> totalAnimales = protectoraHandler.mostrarTodosAnimales();

                            if (totalAnimales.isEmpty()) {
                                System.out.println("No hay animales registrados.");
                                System.out.println("Volviendo al menú");
                                break;
                            }

                            System.out.println("Introduce los valores para actualizar los datos del animal");
                            String continuar="";
                            do{
                                System.out.print("ID del animal: ");
                                int id = Integer.parseInt(sc.nextLine());

                                System.out.print("Nueva edad: ");
                                int nuevaEdad = Integer.parseInt(sc.nextLine());

                                LocalDate fechaActual = LocalDate.now();
                                System.out.print("Nuevo estado (Adoptado / En refugio): ");
                                String nuevoEstado = sc.nextLine();
                                protectoraHandler.updateAnimalDatos( new Animal(id, null, null, nuevaEdad, nuevoEstado, fechaActual));

                                // Preguntar si quiere actualizar otro
                                System.out.println("¿Desea introducir otro animal? (s/n):");
                                continuar = sc.nextLine().trim().toLowerCase();
                            }while  (continuar.equals("s"));
                            System.out.println("Volviendo al menú principal");


                        }

                        case 5 -> {
                            System.out.println(" --> Edad media de animales adoptados <-- ");
                            List<MediaEdadDTO> medias = protectoraHandler.getEdadMediaAdopcion();
                            if (medias.isEmpty()) {
                                System.out.println("No hay registros de adopciones.");
                            } else {
                                System.out.println("Tipo   |  Media edad");
                                System.out.println("--------------------");
                                for (MediaEdadDTO especie : medias) {
                                    // establecemos 2 decimales en el valor de la media
                                    System.out.printf("%-6s |  %.2f años%n", especie.tipo(), especie.mediaEdad());
                                }
                            }
                        }

                        case 6 -> {
                            System.out.println(" --> Consultar adopciones por tipo <-- ");
                            System.out.print("Introduce el tipo de animal (Perro o Gato): ");
                            String tipo = sc.nextLine();

                            List<AdopcionDTO> adopciones = protectoraHandler.getAdopcionesByTipo(tipo);

                            if (adopciones.isEmpty()) {
                                System.out.println("No se han encontrado adopciones para ese tipo.");
                            } else {
                                System.out.println("\nListado de adopciones (" + tipo + "):");
                                for (AdopcionDTO a : adopciones) {
                                    System.out.println(a);
                                }
                            }
                        }

                        case 7 -> System.out.println("Saliendo del programa. Gracias...");
                        default -> System.out.println("Opción no válida");
                    }
                }

                sc.close();
            }

        }

//        ProtectoraLoginHandleDB protectoraH=new ProtectoraLoginHandleDB();
//        var listaAnimal = List.of(
//                new Animal(1, "Elena", "Perro", 20, "En refugio", LocalDate.of(2025, 10, 28)),
//                new Animal(2, "Puskas","Gato",   6, "Adoptado",   LocalDate.of(2024,  6, 11)),
//                new Animal(3, "Rafa",  "Perro", 12, "En refugio", LocalDate.of(2023, 11, 16)),
//                new Animal(4, "Mika",  "Gato",   3, "Adoptado",   LocalDate.of(2021,  4,  5))
//        );

        //Insert
        /* for(Animal pet: listaAnimal){
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
           //System.out.println(protectoraH.getEdadMediaAdopcion());

            //Información del animal adoptado según su tipo getAdopcionesByTipo
        //System.out.println(protectoraH.getAdopcionesByTipo("Perro"));
//        for(AdopcionDTO adopcion: protectoraH.getAdopcionesByTipo("Gato")){
//            System.out.println(adopcion);
//        }

