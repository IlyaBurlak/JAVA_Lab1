// AccountCreation.java
package LAB1.src.UserFunc;

import LAB1.src.DataBaseConector.ConnectorDB;
import LAB1.src.object.Account;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountCreation {
    public static void createAccount(Scanner scanner, int clientId) {
        Account newAccount = new Account();
        newAccount.setClientId(clientId);

        System.out.println("Создание нового счета для пользователя:");
        System.out.print("Тип счета: ");
        newAccount.setAccountType(scanner.nextLine());
        System.out.print("Начальный баланс: ");
        newAccount.setBalance(new BigDecimal(scanner.nextLine()));
        System.out.print("Кредитный лимит: ");
        newAccount.setCreditLimit(new BigDecimal(scanner.nextLine()));
        System.out.print("Начальная сумма вклада: ");
        newAccount.setDepositAmount(new BigDecimal(scanner.nextLine()));
        System.out.print("Процентная ставка: ");
        newAccount.setInterestRate(new BigDecimal(scanner.nextLine()));

        ConnectorDB.createAccount(newAccount);
    }
}