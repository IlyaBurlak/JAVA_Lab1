package DataBaseConector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    private static final String url = "jdbc:postgresql://localhost:5432/Bank";
    private static final String user = "postgres";
    private static final String password = "1207";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database");


        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

}
}
