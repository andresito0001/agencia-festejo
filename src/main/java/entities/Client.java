package main.java.entities;

public class Client {
    public Client(final String ci, final String name, final String lastName, final String direction, final String phoneNumber, final String mail) {
        this.ci = ci;
        this.name = name;
        this.lastName = lastName;
        this.direction = direction;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }
    
    // getters
    public String getCi() { return this.ci; }
    public String getName() { return this.name; }
    public String getLastName() { return this.lastName; }
    public String getDirection() { return this.direction; }
    public String getMail() { return this.mail; }
    public String getPhoneNumber() { return this.phoneNumber; }

    private final String ci;
    private final String name;
    private final String lastName;
    private String direction;
    private String phoneNumber;
    private final String mail;
}