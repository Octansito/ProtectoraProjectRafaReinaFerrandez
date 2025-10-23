package app;
import util.DBConnectionProtectora;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DBConnectionProtectora.getConnection();   // intenta abrir
            if (con != null && !con.isClosed()) {
                System.out.println("Conexión OK");
            } else {
                System.err.println("Conexión NO establecida.");
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        } finally {
            DBConnectionProtectora.close();                  // cierra la única conexión del singleton
        }
    }
}
