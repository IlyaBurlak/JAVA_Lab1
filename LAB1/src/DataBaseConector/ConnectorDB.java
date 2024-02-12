package LAB1.src.DataBaseConector;

import LAB1.src.object.Account;
import LAB1.src.object.Client;

import java.sql.*;

public class ConnectorDB {
    private static final String url = "jdbc:postgresql://localhost:5432/Bank";
    private static final String user = "postgres";
    private static final String password = "1207";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void insertClientToDB(Client client) {
        try (Connection con = getConnection()) {
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
            System.out.println("Ошибка при добавлении клиента в базу данных.");
            e.printStackTrace();
        }
    }

    public static void createAccount(Account account, int bankId) {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO accounts (client_id, account_type, balance, credit_limit, deposit_amount, interest_rate, bank_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, account.getClientId());
            pst.setString(2, account.getAccountType());
            pst.setBigDecimal(3, account.getBalance());
            pst.setBigDecimal(4, account.getCreditLimit());
            pst.setBigDecimal(5, account.getDepositAmount());
            pst.setBigDecimal(6, account.getInterestRate());
            pst.setInt(7, bankId); // Используйте переданный bankId

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Счет успешно создан.");
            } else {
                System.out.println("Не удалось создать счет.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при создании счета.");
            e.printStackTrace();
        }
    }

    public static void loginToAccount(String passportNumber) {
        try (Connection con = getConnection()) {
            String query = "SELECT * FROM clients WHERE passport_number = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, passportNumber);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int clientId = rs.getInt("client_id");
                // Дополнительная логика для входа в аккаунт
                System.out.println("Вход в аккаунт успешно выполнен для клиента с ID: " + clientId);
            } else {
                System.out.println("Клиент с указанным паспортным номером не найден.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при входе в аккаунт.");
            e.printStackTrace();
        }
    }
    public static int getClientIdByPassportNumber(String passportNumber) {
        int clientId = -1;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT client_id FROM clients WHERE passport_number = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, passportNumber);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        clientId = resultSet.getInt("client_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientId;
    }
    public static void getBankListFromDB() {
        try (Connection con = getConnection()) {
            String query = "SELECT name FROM banks";
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String bankName = rs.getString("name");
                System.out.println(bankName);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка банков из базы данных.");
            e.printStackTrace();
        }
    }
    public static void getBankListFromDBWithId() {
        try (Connection con = getConnection()) {
            String query = "SELECT bank_id, name FROM banks";
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int bankId = rs.getInt("bank_id");
                String bankName = rs.getString("name");
                System.out.println( bankId +"." +" " + bankName);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка банков из базы данных.");
            e.printStackTrace();
        }
    }
}