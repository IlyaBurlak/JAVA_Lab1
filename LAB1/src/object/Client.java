package LAB1.src.object;

public class Client {
    private int clientId;
    private String firstName;
    private String lastName;
    private String address;
    private String passportNumber;
    public Client(String firstName, String lastName, String address, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.passportNumber = passportNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getpassportNumber() {
        return passportNumber;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setpassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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
