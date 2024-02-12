package LAB1.src.object;

import java.math.BigDecimal;

public class Bank {
    private int bankId;
    private String name;
    private BigDecimal commission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }
}
