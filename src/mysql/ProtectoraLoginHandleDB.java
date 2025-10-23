package mysql;

import model.Animal;
import util.DBConnectionProtectora;

import java.sql.*;

public class ProtectoraLoginHandleDB {
    final static Connection connection= DBConnectionProtectora.getConnection();

    //Menú CRUD COMPLETO

    public void addAnimal(Animal animal) {
        String insertAnimal ="INSERT INTO animal (nombre, tipo, edad, estado, fecha_ingreso) VALUES (?,?,?,?,?)";

        try(PreparedStatement ps=connection.prepareStatement(insertAnimal, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, animal.getNombreAnimal());
            ps.setString(2, animal.getTipo());
            ps.setInt(3, animal.getEdad());
            ps.setString(4, animal.getEstado());
            ps.setTimestamp(5, Timestamp.valueOf(animal.getFechaIngreso().atStartOfDay()));
            int rows= ps.executeUpdate();

            //Devuelve el primaryKey autoincrementable de la tabla animal
            ResultSet rsAnimal=ps.getGeneratedKeys();
            int idAnimal=0;
            if(rsAnimal.next()){
                idAnimal=rsAnimal.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("No se ha podido añadir al animal");
            e.printStackTrace();
        }
    }

    public void updateAnimal (Animal animal){
        String updateAnimal="UPDATE animal SET nombre=?, tipo=?, edad=?, estado=?, fecha_ingreso=?";
        try(PreparedStatement ps=connection.prepareStatement(updateAnimal)) {

            ps.setString(1, animal.getNombreAnimal());
            ps.setString(2, animal.getTipo());
            ps.setInt(3, animal.getEdad());
            ps.setString(4, animal.getEstado());
            ps.setTimestamp(5, Timestamp.valueOf(animal.getFechaIngreso().atStartOfDay()));
            int rows= ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el animal");
            e.printStackTrace();
        }
    }


    public void deleteAnimal(Animal animal){
        String deleteAnimal="DELETE FROM animal WHERE id_animal=?";
        try(PreparedStatement ps=connection.prepareStatement(deleteAnimal)) {
            ps.setInt(1,animal.getIdAnimal());
            int rows=ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar al animal");
            e.printStackTrace();
        }
    }
}
