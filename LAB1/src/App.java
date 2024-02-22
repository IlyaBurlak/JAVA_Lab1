package LAB1.src;

import LAB1.src.MenuList.BankModeManager;
import LAB1.src.MenuList.UserAccountManager;
import LAB1.src.UserFunc.ClientRegistration;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankModeManager bankModeManager = new BankModeManager();

        boolean firstTime = bankModeManager.isFirstTimeInBank(scanner);

        if (firstTime) {
            ClientRegistration.registerNewClient(scanner);
        } else {
            UserAccountManager userAccountManager = new UserAccountManager();
            userAccountManager.displayMainMenu(scanner);
        }
    }
}