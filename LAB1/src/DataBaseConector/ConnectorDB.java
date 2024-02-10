package LAB1.src.DataBaseConector;

import LAB1.src.object.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectorDB {
    private static final String url = "jdbc:postgresql://localhost:5432/Bank";
    private static final String user = "postgres";
    private static final String password = "1207";

    public static void insertClientToDB(Client client) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO clients (first_name, last_name, address, passport_number) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, client.getFirstName());
            pst.setString(2, client.getLastName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getpassportNumber());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Клиент успешно добавлен в базу данных.");
            } else {
                System.out.println("Не удалось добавить клиента в базу данных.");
            }

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}

