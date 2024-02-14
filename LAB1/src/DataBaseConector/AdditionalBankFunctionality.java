package LAB1.src.DataBaseConector;
import java.sql.*;

public class AdditionalBankFunctionality {

    public static void createNewBank(String bankName, double commission) {
        try (Connection con = ConnectorDB.getConnection()) {
            String query = "INSERT INTO banks (name, commission) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bankName);
            pst.setDouble(2, commission);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Новый банк успешно создан: " + bankName + " с комиссией " + commission + "%");
            } else {
                System.out.println("Не удалось создать новый банк.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при создании нового банка.");
            e.printStackTrace();
        }
    }

    public static void displayBankListWithIdAndCommission() {
        System.out.println("Список банков с их ID и комиссией:");
        try {
            Connection con = ConnectorDB.getConnection();
            String query = "SELECT bank_id, name, commission FROM banks";
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int bankId = rs.getInt("bank_id");
                String bankName = rs.getString("name");
                double commission = rs.getDouble("commission");

                System.out.println(bankId + ". " + bankName + " (Комиссия: " + commission + "%)");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка банков из базы данных.");
            e.printStackTrace();
        }
    }
}