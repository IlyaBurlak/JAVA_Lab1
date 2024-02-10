package object;

public class Client {
    private int clientId;
    private String firstName;
    private String lastName;
    private String address;
    private String pasportNumber;

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPasportNumber() {
        return pasportNumber;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setPasportNumber(String pasportNumber) {
        this.pasportNumber = pasportNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
