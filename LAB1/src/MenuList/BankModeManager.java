package LAB1.src.MenuList;

import LAB1.src.DataBaseConector.AdditionalBankFunctionality;

import java.util.Scanner;

public class BankModeManager {
    public static boolean isFirstTimeInBank(Scanner scanner) {
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

    public static void displayBankMainMenu(Scanner scanner) {
        boolean exitBankMode = false;

        while (!exitBankMode) {
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
                    exitBankMode = true;
                    break;
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите действие из списка.");
            }
        }
    }


}
