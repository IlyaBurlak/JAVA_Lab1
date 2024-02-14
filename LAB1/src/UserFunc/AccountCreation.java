package LAB1.src.UserFunc;

import LAB1.src.DataBaseConector.ConnectorDB;
import LAB1.src.object.Account;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountCreation {
    public static void createAccount(Scanner scanner, int clientId) {
        Account newAccount = new Account();
        newAccount.setClientId(clientId);

        System.out.println("Список банков:");
        ConnectorDB.getBankListFromDBWithId();

        System.out.println("Выберите ID банка:");
        int bankId = Integer.parseInt(scanner.nextLine());

        System.out.println("Выберите тип счета:");
        System.out.println("1. Дебетовый счет");
        System.out.println("2. Депозитный счет");
        System.out.println("3. Кредитный счет");
        System.out.print("Ваш выбор: ");
        int accountTypeChoice = Integer.parseInt(scanner.nextLine());



        switch (accountTypeChoice) {
            case 1:
                newAccount.setAccountType("Дебетовый счет");
                newAccount.setBalance(BigDecimal.ZERO);
                newAccount.setCreditLimit(BigDecimal.ZERO);
                newAccount.setDepositAmount(BigDecimal.ZERO);
                newAccount.setInterestRate(new BigDecimal("2.5"));
                break;
            case 2:
                newAccount.setAccountType("Депозитный счет");
                newAccount.setBalance(BigDecimal.ZERO);
                newAccount.setCreditLimit(BigDecimal.ZERO);
                newAccount.setDepositAmount(BigDecimal.ZERO);
                System.out.println("Введите сумму депозита: ");
                newAccount.setDepositAmount(new BigDecimal(scanner.nextLine()));
                if (newAccount.getDepositAmount().compareTo(new BigDecimal("50000")) < 0) {
                    newAccount.setInterestRate(new BigDecimal("3.0"));
                } else if (newAccount.getDepositAmount().compareTo(new BigDecimal("100000")) < 0) {
                    newAccount.setInterestRate(new BigDecimal("3.5"));
                } else {
                    newAccount.setInterestRate(new BigDecimal("4.0"));
                }
                break;
            case 3:
                newAccount.setAccountType("Кредитный счет");
                System.out.println("Введите кредитный лимит: ");
                newAccount.setCreditLimit(new BigDecimal(scanner.nextLine()));
                newAccount.setBalance(BigDecimal.ZERO);
                newAccount.setDepositAmount(BigDecimal.ZERO);
                newAccount.setInterestRate(BigDecimal.ZERO);
                break;
            default:
                System.out.println("Некорректный выбор типа счета.");
                return;
        }

        ConnectorDB.createAccount(newAccount , bankId);
    }
}
