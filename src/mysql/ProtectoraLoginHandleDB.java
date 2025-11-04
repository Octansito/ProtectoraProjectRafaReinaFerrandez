package mysql;

import dto.AdopcionDTO;
import dto.MediaEdadDTO;
import model.Animal;
import util.DBConnectionProtectora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ProtectoraLoginHandleDB {
    final static Connection connection= DBConnectionProtectora.getConnection();

    //Menú CRUD COMPLETO

    /**
     * Inserta un nuevo registro en la tabla animal con sus propiedades correspondientes
     * @param animal
     */
    public void insertAnimal(Animal animal) {

        String insertAnimal ="INSERT INTO animal (nombre, tipo, edad, estado, fecha_ingreso) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(insertAnimal, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
     * Actualiza el estado de un animal según la id de dicho animal
     * @param animal
     */
    public void updateAnimalEstado (Animal animal){
        String updateAnimal="UPDATE animal SET estado = ? WHERE id_animal = ?";
        try(PreparedStatement ps=connection.prepareStatement(updateAnimal)) {
            connection.setAutoCommit(false);
            ps.setString(1, animal.getEstado());
            ps.setInt(2, animal.getIdAnimal());
            ps.executeUpdate();
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
     * Elimina de la tabla el animmal que coincide con la id
     */
    public void deleteAnimal(Animal animal){
        String deleteAnimal="DELETE FROM animal WHERE id_animal=?";
        try(PreparedStatement ps=connection.prepareStatement(deleteAnimal)) {
            connection.setAutoCommit(false);
            ps.setInt(1,animal.getIdAnimal());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar al animal");
            e.printStackTrace();

            try{
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: "+ex.getMessage());
            }

        } finally {
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
