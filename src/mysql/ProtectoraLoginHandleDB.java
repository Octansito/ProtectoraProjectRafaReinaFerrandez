package mysql;

import model.Animal;
import util.DBConnectionProtectora;

import java.sql.*;

public class ProtectoraLoginHandleDB {
    final static Connection connection= DBConnectionProtectora.getConnection();

    //Menú CRUD COMPLETO
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

        } catch (SQLException e) {
            System.err.println("Error al insertar el animal en la base de datos");
            e.printStackTrace();

            try{
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: "+ex.getMessage());
            }

        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Error al restaurar autoCommit: " + ex.getMessage());
            }
        }



    }

    public void updateAnimal (Animal animal){
        String updateAnimal="UPDATE animal SET nombre=?, tipo=?, edad=?, estado=?, fecha_ingreso=?";
        try(PreparedStatement ps=connection.prepareStatement(updateAnimal)) {
            connection.setAutoCommit(false);

            ps.setString(1, animal.getNombreAnimal());
            ps.setString(2, animal.getTipo());
            ps.setInt(3, animal.getEdad());
            ps.setString(4, animal.getEstado());
            ps.setTimestamp(5, Timestamp.valueOf(animal.getFechaIngreso().atStartOfDay()));
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el animal");
            e.printStackTrace();

            try{
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: "+ex.getMessage());
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Error al restaurar autoCommit: " + ex.getMessage());
            }
        }
    }

    public void deleteAnimal(Animal animal){
        String deleteAnimal="DELETE FROM animal WHERE id_animal=?";
        try(PreparedStatement ps=connection.prepareStatement(deleteAnimal)) {
            connection.setAutoCommit(false);

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


}
