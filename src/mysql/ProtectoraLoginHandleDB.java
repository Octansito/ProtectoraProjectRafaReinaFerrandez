package mysql;

import dto.AdopcionDTO;
import dto.MediaEdadDTO;
import model.Animal;
import util.DBConnectionProtectora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProtectoraLoginHandleDB {
    final static Connection connection= DBConnectionProtectora.getConnection();

    //Menú CRUD COMPLETO

    /**
     * Inserta un nuevo registro en la tabla animal con sus propiedades correspondientes
     * @param animal
     */
    public void insertAnimal(Animal animal) {

        String insertAnimal ="INSERT INTO animal (nombre, tipo, edad, estado, fecha_ingreso) VALUES (?,?,?,?,?)";
        String insertAdopcion="INSERT INTO adopcion(id_animal, nombre_adoptante, telefono, fecha_adopcion, direccion) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(insertAnimal, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement psAdopcion=connection.prepareStatement(insertAdopcion)) {
            connection.setAutoCommit(false);
            ps.setString(1, animal.getNombreAnimal());
            ps.setString(2, animal.getTipo());
            ps.setInt(3, animal.getEdad());
            ps.setString(4, animal.getEstado());
            ps.setTimestamp(5, Timestamp.valueOf(animal.getFechaIngreso().atStartOfDay()));
            ps.executeUpdate();

            //Devuelve el primaryKey autoincrementable de la tabla animal
            ResultSet rsAnimal=ps.getGeneratedKeys();
            int idAnimal=0;
            if(rsAnimal.next()){
                idAnimal=rsAnimal.getInt(1);
            }

            // --- Si el animal está adoptado, registrar la adopción ---
            if ("Adoptado".equalsIgnoreCase(animal.getEstado())) {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n--- Registrar adopción del animal " + animal.getNombreAnimal() + " ---");
                System.out.print("Nombre del adoptante: ");
                String nombreAdoptante = sc.nextLine();
                System.out.print("Teléfono del adoptante: ");
                String telefono = sc.nextLine();
                System.out.print("Dirección del adoptante: ");
                String direccion = sc.nextLine();

                psAdopcion.setInt(1, idAnimal);
                psAdopcion.setString(2, nombreAdoptante);
                psAdopcion.setString(3, telefono);
                psAdopcion.setTimestamp(4, Timestamp.valueOf(animal.getFechaIngreso().atStartOfDay()));
                psAdopcion.setString(5, direccion);
                psAdopcion.executeUpdate();

                System.out.println("Animal y adopción registrados correctamente.");
            } else {
                System.out.println("Animal registrado correctamente en refugio.");
            }

            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error al insertar el animal en la base de datos");
            e.printStackTrace();
            try{
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: "+ex.getMessage());
            }

        }finally {
            try{
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Error al restaurar autoCommit: "+ex.getMessage());
            }
        }

    }

    /**
     * Actualiza el estado o la edad de un animal según la id de dicho animal
     * @param animal
     */
    public void updateAnimalDatos (Animal animal){
        String updateAnimal="UPDATE animal SET edad=?, estado = ? WHERE id_animal = ?";
        String insertAdopcion = "INSERT INTO adopcion (id_animal, nombre_adoptante, telefono, fecha_adopcion, direccion) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(updateAnimal);
            PreparedStatement psAdopcion = connection.prepareStatement(insertAdopcion)
        ) {
            connection.setAutoCommit(false);
            ps.setInt(1, animal.getEdad());
            ps.setString(2, animal.getEstado());
            ps.setInt(3, animal.getIdAnimal());
            ps.executeUpdate();

            // --- Si el estado es "Adoptado", registrar adopción ---
            if ("Adoptado".equalsIgnoreCase(animal.getEstado())) {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n--- Registrar adopción del animal con ID " + animal.getIdAnimal() + " ---");
                System.out.print("Nombre del adoptante: ");
                String nombreAdoptante = sc.nextLine();
                System.out.print("Teléfono del adoptante: ");
                String telefono = sc.nextLine();
                System.out.print("Dirección del adoptante: ");
                String direccion = sc.nextLine();

                psAdopcion.setInt(1, animal.getIdAnimal());
                psAdopcion.setString(2, nombreAdoptante);
                psAdopcion.setString(3, telefono);
                psAdopcion.setTimestamp(4, Timestamp.valueOf(animal.getFechaIngreso().atStartOfDay()));
                psAdopcion.setString(5, direccion);
                psAdopcion.executeUpdate();

                System.out.println("Animal actualizado y adopción registrada correctamente.");
            } else {
                System.out.println("Datos del animal actualizados correctamente.");
            }

            connection.commit();

        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el animal");
            e.printStackTrace();

            try{
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: "+ex.getMessage());
            }

        }finally {
            try{
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Error al restaurar autoCommit: "+ex.getMessage());
            }
        }
    }

    /**
     * Método para mostar la lista de animales en estado de adopción
     */
    public List<Integer> mostrarAnimalesEnRefugio() {
        String query = "SELECT id_animal, nombre FROM animal WHERE estado = 'En refugio'";
        List<Integer> listaIds = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             System.out.println("\n--- Animales en refugio (eliminables) ---");
             System.out.printf("%-5s %-15s%n", "ID", "Nombre");
             System.out.println("---------------------------");
             while (rs.next()) {
                int id = rs.getInt("id_animal");
                String nombre = rs.getString("nombre");
                System.out.printf("%-5d %-15s%n", id, nombre);
                listaIds.add(id);
             }
        } catch (SQLException e) {
            System.err.println("Error al listar animales en refugio");
            e.printStackTrace();
        }
        return listaIds;
    }


    /**
     * Elimina un animal de la base de datos solo si su estado es "En refugio".
     * Si el animal está adoptado, no se permite el borrado por integridad referencial.
     */
    public void deleteAnimal(int idAnimal){
        String checkEstado = "SELECT estado FROM animal WHERE id_animal= ?";
        String deleteAnimal="DELETE FROM animal WHERE id_animal=?";
        try(PreparedStatement psCheck = connection.prepareStatement(checkEstado);
            PreparedStatement psDelete=connection.prepareStatement(deleteAnimal)) {

            //Se comprueba si existe el animal y su estado
            psCheck.setInt(1, idAnimal);
            ResultSet rsCheck=psCheck.executeQuery();
            while(!rsCheck.next()){
                System.out.println("No existe un animal con ese ID.");
                return;
            }
            String estado = rsCheck.getString("estado");
            if (!estado.equalsIgnoreCase("En refugio")) {
                System.out.println("No se puede eliminar: el animal no está en el refugio");
                return;
            }
            // --- Eliminar animal seleccionado ---
            connection.setAutoCommit(false);
            psDelete.setInt(1, idAnimal);
            psDelete.executeUpdate();
            connection.commit();

            System.out.println("Animal eliminado correctamente del refugio.");


        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar al animal");
            e.printStackTrace();
            try{
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: "+ex.getMessage());
            }

        }finally {
            try{
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Error al restaurar autoCommit: "+ex.getMessage());
            }
        }

    }

    /**
     * Consulta que devuelve la media de edad de los tipos de animales adoptados

     * @return devuelve el número de filas asociadasa a animales con ese parámetro
     */
    public List<MediaEdadDTO> getEdadMediaAdopcion(){
        List<MediaEdadDTO> resultado= new ArrayList<>();
        String totalQuery="SELECT an.tipo, AVG(an.edad) AS media_edad FROM adopcion ad JOIN animal an ON an.id_animal = ad.id_animal GROUP BY an.tipo ORDER BY media_edad DESC";
        try (PreparedStatement ps= connection.prepareStatement(totalQuery)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                resultado.add(new MediaEdadDTO(rs.getString(1), rs.getDouble(2)));
            }
        } catch (SQLException e) {
            System.err.println("No se ha podido obtener la media de edad por tipo de animal");
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Consulta todas las adopciones por tipo de animal
     * @param tipo del animal "Perro" o "Gato"
     * @return devuelve una lista con por cada adopción: el id, el nombre del animal, tipo, nombre del adoptante y fecha de adopción
     */
    public List<AdopcionDTO> getAdopcionesByTipo(String tipo){
        List<AdopcionDTO> listaAdopciones= new ArrayList<>();
        String queryAdopciones="SELECT ad.id_adopcion, an.nombre, an.tipo, ad.nombre_adoptante, ad.fecha_adopcion FROM adopcion ad JOIN animal an ON an.id_animal = ad.id_animal WHERE an.tipo = ? ORDER BY ad.fecha_adopcion ASC";
        try(PreparedStatement ps=connection.prepareStatement(queryAdopciones)){

            ps.setString(1, tipo);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                listaAdopciones.add(new AdopcionDTO(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getTimestamp(5).toLocalDateTime().toLocalDate()));

            }

        } catch (SQLException e) {
            System.err.println("No se ha podido obtener el total de adopciones por tipo");
            e.printStackTrace();
        }
        return listaAdopciones;
    }

}
