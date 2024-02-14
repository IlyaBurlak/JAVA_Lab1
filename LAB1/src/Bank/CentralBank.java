package LAB1.src.Bank;

import LAB1.src.object.Bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private List<Bank> registeredBanks;

    public CentralBank() {
        this.registeredBanks = new ArrayList<>();
    }

    public void registerBank(Bank bank) {
        registeredBanks.add(bank);
        System.out.println("Bank registered: " + bank.getName());
    }

    public void transferFunds(Bank senderBank, Bank receiverBank, BigDecimal amount) {
        // Transfer logic between banks
        System.out.println("Transferring funds from " + senderBank.getName() + " to " + receiverBank.getName() + ": " + amount);
    }

    public void notifyBank(Bank bank, BigDecimal amount, String message) {
        System.out.println("Notification sent to " + bank.getName() + ": " + message + " Amount: " + amount);
    }

    public void updateCommissions(BigDecimal newCommission) {
        for (Bank bank : registeredBanks) {
            bank.setCommission(newCommission);
        }
        System.out.println("Commissions updated for all banks.");
    }
}