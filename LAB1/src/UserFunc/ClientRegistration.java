package LAB1.src.UserFunc;

import LAB1.src.DataBaseConector.ConnectorDB;
import LAB1.src.object.Client;

import java.util.Scanner;

public class ClientRegistration {
    public static void registerNewClient(Scanner scanner) {
        System.out.println("Добро пожаловать! Для создания клиента введите следующие данные");
        System.out.print("Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();

        System.out.print("Адрес (нажмите Enter, чтобы пропустить): ");
        String address = scanner.nextLine();

        System.out.print("Номер паспорта (нажмите Enter, чтобы пропустить): ");
        String passportNumber = scanner.nextLine();

        Client client = new Client(firstName, lastName, address, passportNumber);

        ConnectorDB.insertClientToDB(client);

        System.out.println("Клиент успешно добавлен в базу данных.");
        System.out.println("Спасибо за регистрацию!");
    }
}