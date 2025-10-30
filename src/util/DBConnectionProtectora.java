package util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creación de clase Singleton para gestionar la conexión a la BD
 */
public class DBConnectionProtectora {
    public static Connection connection=null;

    public DBConnectionProtectora() {}

    public static Connection getConnection(){

        if(connection==null){
            try {
                    Properties props=new Properties();

                    //Lectura del fichero config.properties para obtener los valores del fichero
                    try(FileInputStream file=new FileInputStream("src/config.properties")) {
                        props.load(file);

                    }

                    String url=props.getProperty("db.url");
                    String username=props.getProperty("db.username");
                    String password=props.getProperty("db.password");

                    connection= DriverManager.getConnection(url,username,password);
                    System.out.println("Conexión establecida correctamente con la base de datos");


            } catch (FileNotFoundException e) {
                System.err.println("No se encontró el fichero config.properties "+e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al leer el fichero config.properties "+e.getMessage() );
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos. Revisa la URL "+e.getMessage());
                e.printStackTrace(); // ← verás la clase y la línea (DriverManager.getConnection)
            }
        }
        return connection;
    }


    //Forma de cerrar la conexión

    public static void close(){
        if(connection!=null){
            try{
                connection.close();
                connection=null;
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión "+e.getMessage());
            }
        }
    }

}
