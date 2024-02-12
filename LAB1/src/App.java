package LAB1.src;

import LAB1.src.DataBaseConector.ConnectorDB;
import LAB1.src.UserFunc.ClientRegistration;
import LAB1.src.UserFunc.AccountCreation;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayMainMenu(scanner);
    }

    private static void displayMainMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.print("В первый раз в нашем банке? (да/нет): ");
            String firstTimeResponse = scanner.nextLine();

            switch (firstTimeResponse.toLowerCase()) {
                case "да":
                    ClientRegistration.registerNewClient(scanner);
                    break;
                case "нет":
                    System.out.println("Мы рады видеть вас снова! Желаете войти в аккаунт или создать нового пользователя?");
                    System.out.print("Выберите действие:\n 1.Вход \n 2.Регистрация\n 3.Выход\n ");
                    String action = scanner.nextLine();

                    switch (action) {
                        case "1":
                            System.out.print("Введите номер паспорта для входа: ");
                            String passportNumber1 = scanner.nextLine();
                            int clientId = ConnectorDB.getClientIdByPassportNumber(passportNumber1);
                            if (clientId != -1) {
                                // todo smt
                                System.out.println("Возвращаемся к выбору действия.");
                            } else {
                                System.out.println("Клиент с указанным паспортом не найден.");
                            }
                            break;
                        case "2":
                            System.out.print("Введите номер паспорта клиента: ");
                            String passportNumber2 = scanner.nextLine();
                            int clientId2 = ConnectorDB.getClientIdByPassportNumber(passportNumber2);
                            if (clientId2 != -1) {
                                AccountCreation.createAccount(scanner, clientId2);
                                System.out.println("Возвращаемся к выбору действия.");
                            } else {
                                System.out.println("Клиент с указанным паспортом не найден.");
                            }
                            break;
                        case "3":
                            System.out.println("До свидания!");
                            exit = true;
                            break;
                        default:
                            System.out.println("Некорректный выбор действия.");
                            break;
                    }
                    break;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, введите 'да' или 'нет'.");
                    break;
            }
        }
    }
}