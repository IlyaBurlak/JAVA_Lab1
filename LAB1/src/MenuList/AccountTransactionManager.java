package LAB1.src.MenuList;

import LAB1.src.DataBaseConector.ConnectorDB;

import java.util.Scanner;

public class AccountTransactionManager {
    public static void handleAccountTransactions(Scanner scanner, String passportNumber) {
        ConnectorDB.displayUserAccounts(passportNumber);

        System.out.print("Введите номер аккаунта, с которым хотите взаимодействовать: ");
        int accountId = Integer.parseInt(scanner.nextLine());

        boolean exit = false;
        while (!exit) {
            System.out.println("Выберите действие для аккаунта " + accountId + ":\n 1. Пополнение \n 2. Списание\n 3. Вернуться в главное меню");

            String action = scanner.nextLine();

            switch (action) {
                case "1":
                    System.out.print("Введите сумму для пополнения: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    ConnectorDB.depositToAccount(accountId, depositAmount);
                    break;
                case "2":
                    System.out.print("Введите сумму для списания: ");
                    double withdrawalAmount = Double.parseDouble(scanner.nextLine());
                    ConnectorDB.withdrawFromAccount(accountId, withdrawalAmount);
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор действия.");
                    break;
            }
        }
    }
}
