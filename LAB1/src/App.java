package LAB1.src;

import LAB1.src.DataBaseConector.ConnectorDB;
import LAB1.src.UserFunc.ClientRegistration;
import LAB1.src.UserFunc.AccountCreation;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("В первый раз в нашем банке? (да/нет): ");
        String firstTimeResponse = scanner.nextLine();

        if (firstTimeResponse.equalsIgnoreCase("да")) {
            ClientRegistration.registerNewClient(scanner);
        } else if (firstTimeResponse.equalsIgnoreCase("нет")) {
            System.out.println("Мы рады видеть вас снова! Желаете войти в аккаунт или создать нового пользователя?");
            System.out.print("Выберите действие:\n 1.Вход \n 2.Регистрация\n ");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("1")) {
                System.out.print("Введите номер паспорта для входа: ");
                String passportNumber = scanner.nextLine();
                int clientId = ConnectorDB.getClientIdByPassportNumber(passportNumber);
                if (clientId != -1) {
                    // Implement login functionality with client ID
                } else {
                    System.out.println("Клиент с указанным паспортом не найден.");
                }
            } else if (action.equalsIgnoreCase("2")) {

                System.out.print("Введите номер паспорта клиента: ");
                String passportNumber = scanner.nextLine();

                int clientId = ConnectorDB.getClientIdByPassportNumber(passportNumber);
                if (clientId != -1) {
                    // Create a new account for the user through the AccountCreation class
                    AccountCreation.createAccount(scanner, clientId);
                } else {
                    System.out.println("Клиент с указанным паспортом не найден.");
                }
            } else {
                System.out.println("Некорректный выбор действия.");
            }
        } else {
            System.out.println("Некорректный ввод. Пожалуйста, введите 'да' или 'нет'.");
        }
    }
}