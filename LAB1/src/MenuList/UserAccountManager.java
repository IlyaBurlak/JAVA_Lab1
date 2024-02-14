package LAB1.src.MenuList;

import LAB1.src.DataBaseConector.ConnectorDB;
import LAB1.src.UserFunc.AccountCreation;

import java.util.Scanner;

import static LAB1.src.MenuList.AccountTransactionManager.handleAccountTransactions;

public class UserAccountManager {
    public static void displayMainMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Мы рады видеть вас снова! Желаете войти в аккаунт или создать нового пользователя?");
            System.out.print("Выберите действие:\n 1.Вход \n 2.Регистрация\n 3.Выход\n ");
            String action = scanner.nextLine();

            switch (action) {
                case "1":
                    loginAccount(scanner);
                    break;
                case "2":
                    registerAccount(scanner);
                    break;
                case "3":
                    System.out.println("До свидания!");
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор действия.");
                    break;
            }
        }
    }

    public static void loginAccount(Scanner scanner) {
        System.out.print("Введите номер паспорта для входа: ");
        String passportNumber = scanner.nextLine();
        int clientId = ConnectorDB.getClientIdByPassportNumber(passportNumber);

        if (clientId != -1) {
            handleAccountTransactions(scanner, passportNumber);
            System.out.println("Возвращаемся к выбору действия.");
        } else {
            System.out.println("Аккаунт с указанным номером паспорта не найден.");
        }
    }
    public static void registerAccount(Scanner scanner) {
        System.out.print("Введите номер паспорта клиента: ");
        String passportNumber = scanner.nextLine();
        int clientId = ConnectorDB.getClientIdByPassportNumber(passportNumber);

        if (clientId != -1) {
            AccountCreation.createAccount(scanner, clientId);
            System.out.println("Возвращаемся к выбору действия.");
        } else {
            System.out.println("Клиент с указанным паспортом не найден.");
        }
    }
}
