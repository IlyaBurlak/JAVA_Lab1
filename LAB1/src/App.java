// App.java
package LAB1.src;

import LAB1.src.UserFunc.ClientRegistration;

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
        } else {
            System.out.println("Некорректный ввод. Пожалуйста, введите 'да' или 'нет'.");
        }
    }
}