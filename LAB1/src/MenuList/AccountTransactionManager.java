package LAB1.src.MenuList;

import LAB1.src.DataBaseConector.ConnectorDB;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountTransactionManager {
    public static void handleAccountTransactions(Scanner scanner, String passportNumber) {
        ConnectorDB.displayUserAccounts(passportNumber);

        System.out.print("Введите номер аккаунта, с которым хотите взаимодействовать: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        String accountType = ConnectorDB.getAccountType(accountId);

        if (accountType == null) {
            System.out.println("Аккаунт с номером " + accountId + " не найден.");
            return;
        }

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
                    if (accountType.equals("Дебетовый счет")) {
                        BigDecimal currentBalance = ConnectorDB.getAccountBalance(accountId);
                        System.out.println("Текущий баланс на счете: " + currentBalance);

                        System.out.print("Введите сумму для списания: ");
                        double withdrawalAmount = Double.parseDouble(scanner.nextLine());

                        if (withdrawalAmount > 0 && BigDecimal.valueOf(withdrawalAmount).compareTo(currentBalance) <= 0) {
                            ConnectorDB.withdrawFromAccount(accountId, withdrawalAmount);
                        } else {
                            System.out.println("Некорректная сумма для списания. Убедитесь, что у вас достаточно средств и введите положительную сумму.");
                        }
                    } else {
                        System.out.println("Вы не можете уйти в минус по балансу на дебетовом счете.");
                    }
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