package LAB1.src;

import LAB1.src.DataBaseConector.AdditionalBankFunctionality;
import LAB1.src.DataBaseConector.ConnectorDB;
import LAB1.src.UserFunc.ClientRegistration;
import LAB1.src.UserFunc.AccountCreation;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean firstTime = isFirstTimeInBank(scanner);

        if (firstTime) {
            ClientRegistration.registerNewClient(scanner);
        } else {
            displayMainMenu(scanner);
        }


    }

    private static boolean isFirstTimeInBank(Scanner scanner) {
        System.out.print("Вы первый раз в нашем банке? (да/нет): ");
        String firstTimeResponse = scanner.nextLine().toLowerCase();

        if ("BankMode".equalsIgnoreCase(firstTimeResponse)) {
            displayBankMainMenu(scanner);
            return true;
        }

        while (!firstTimeResponse.equals("да") && !firstTimeResponse.equals("нет")) {
            System.out.println("Некорректный ввод. Пожалуйста, введите 'да' или 'нет'.");
            System.out.print("Вы первый раз в нашем банке? (да/нет): ");
            firstTimeResponse = scanner.nextLine().toLowerCase();
        }

        return false;
    }

    private static void displayBankMainMenu(Scanner scanner) {
        System.out.println("---------------------------");
        System.out.println("WARNING Вы в режиме банка!");
        System.out.println("--------------------------");
        System.out.println("Выберите действие:");
        System.out.println("1. Создать новый банк");
        System.out.println("2. Показать список банков с ID и комиссией");
        System.out.println("3. Выход");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Введите название банка: ");
                String bankName = scanner.nextLine();

                System.out.print("Введите комиссию банка (%): ");
                double commission = Double.parseDouble(scanner.nextLine());

                AdditionalBankFunctionality.createNewBank(bankName, commission);
                break;
            case 2:
                AdditionalBankFunctionality.displayBankListWithIdAndCommission();
                break;
            case 3:
                System.out.println("До свидания!");
                break;
            default:
                System.out.println("Некорректный выбор. Пожалуйста, выберите действие из списка.");
        }
    }


    private static void displayMainMenu(Scanner scanner) {
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

    private static void loginAccount(Scanner scanner) {
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

    private static void registerAccount(Scanner scanner) {
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

    private static void handleAccountTransactions(Scanner scanner, String passportNumber) {
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
