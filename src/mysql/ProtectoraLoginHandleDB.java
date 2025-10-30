package mysql;

import dto.AdopcionDTO;
import model.Animal;
import util.DBConnectionProtectora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProtectoraLoginHandleDB {
    final static Connection connection= DBConnectionProtectora.getConnection();

    //Menú CRUD COMPLETO
    public void insertAnimal(Animal animal) {

        String insertAnimal ="INSERT INTO animal (nombre, tipo, edad, estado, fecha_ingreso) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(insertAnimal, PreparedStatement.RETURN_GENERATED_KEYS)) {



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

        } catch (SQLException e) {
            System.err.println("Error al insertar el animal en la base de datos");
            e.printStackTrace();

        }

    }

    public void updateAnimalEstado (Animal animal){
        String updateAnimal="UPDATE animal an JOIN adopcion ad ON ad.id_animal= an.id_animal SET an.estado = ? WHERE ad.id_animal = ?";
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

    public void deleteAnimal(Animal animal){
        String deleteAnimal="DELETE FROM animal WHERE id_animal=?";
        try(PreparedStatement ps=connection.prepareStatement(deleteAnimal)) {
            ps.setInt(1,animal.getIdAnimal());
            ps.executeUpdate();

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

    //
    public int getTotalByEstado(String estado){
        String totalQuery="SELECT an.estado, COUNT(*) AS total_estado FROM adopcion ad JOIN animal an ON an.id_animal = ad.id_animal WHERE an.estado = ? GROUP BY an.estado";
        try (PreparedStatement ps= connection.prepareStatement(totalQuery)){
            ps.setString(1, estado);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                return rs.getInt("total_estado");
            }
        } catch (SQLException e) {
            System.err.println("No se ha podido obtener el total de animales por estado");
            e.printStackTrace();
        }
        return 0;
    }

    //
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
