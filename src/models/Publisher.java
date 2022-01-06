package models;

import interfaces.IPublisher;

public class Publisher implements IPublisher {
    String name;
    String address;
    String telephoneNumber;

    public Publisher(String name, String address, String telephoneNumber) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }
}
