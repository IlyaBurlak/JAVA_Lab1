package LAB1.src.DataBaseConector;

import LAB1.src.object.Account;
import LAB1.src.object.Client;

import java.math.BigDecimal;
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
            pst.setInt(7, bankId);

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
    public static void displayUserAccounts(String passportNumber) {
        try (Connection con = getConnection()) {
            String query = "SELECT account_id, account_type, balance FROM accounts WHERE client_id IN (SELECT client_id FROM clients WHERE passport_number = ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, passportNumber);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int accountId = rs.getInt("account_id");
                String accountType = rs.getString("account_type");
                BigDecimal balance = rs.getBigDecimal("balance");

                System.out.println("Аккаунт ID: " + accountId + ", Тип аккаунта: " + accountType + ", Баланс: " + balance);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выводе аккаунтов пользователя.");
            e.printStackTrace();
        }
    }

    public static void withdrawFromAccount(int accountId, double amount) {
        if (amount < 0) {
            System.out.println("Ошибка: Нельзя снять со счета отрицательную сумму.");
            return;
        }
        try {
            Connection con = ConnectorDB.getConnection();
            String query = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setBigDecimal(1, BigDecimal.valueOf(amount));
            pst.setInt(2, accountId);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Со счета " + accountId + " списано " + amount);
            } else {
                System.out.println("Не удалось выполнить списание средств.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при списании средств со счета.");
            e.printStackTrace();
        }
    }
    public static void depositToAccount(int accountId, double amount) {
        if (amount < 0) {
            System.out.println("Ошибка: Нельзя пополнять счет отрицательной суммой.");
            return;
        }
        try {
            Connection con = ConnectorDB.getConnection();
            String query = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setBigDecimal(1, BigDecimal.valueOf(amount));
            pst.setInt(2, accountId);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Средства успешно пополнены на аккаунт " + accountId + " на сумму " + amount);
            } else {
                System.out.println("Не удалось пополнить счет.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при пополнении счета.");
            e.printStackTrace();
        }
    }

    public static String getAccountType(int accountId) {
        String accountType = null;

        try (Connection con = getConnection()) {
            String query = "SELECT account_type FROM accounts WHERE account_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, accountId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                accountType = rs.getString("account_type");
            } else {
                System.out.println("Аккаунт с ID " + accountId + " не найден.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении типа аккаунта.");
            e.printStackTrace();
        }

        return accountType;
    }
    public static BigDecimal getAccountBalance(int accountId) {
        BigDecimal accountBalance = null;

        try (Connection con = getConnection()) {
            String query = "SELECT balance FROM accounts WHERE account_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, accountId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                accountBalance = rs.getBigDecimal("balance");
            } else {
                System.out.println("Аккаунт с ID " + accountId + " не найден.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении баланса аккаунта.");
            e.printStackTrace();
        }

        return accountBalance;
    }
    public static void updateAccountBalancesWithDividends() {
        try (Connection con = getConnection()) {
            String query = "UPDATE accounts SET balance = balance + (balance * (SELECT SUM(dividends) FROM banks WHERE bank_id = bank_id))";
            PreparedStatement pst = con.prepareStatement(query);

            int rowsAffected = pst.executeUpdate();
            System.out.println("Дивиденды успешно начислены по всем счетам.");
        } catch (SQLException e) {
            System.out.println("Ошибка при начислении дивидендов по счетам.");
            e.printStackTrace();
        }
    }

}