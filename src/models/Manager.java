package models;

import interfaces.IUser;

public class Manager extends IUser {
    public Manager(String firstName, String lastName, String password, String emailAddress, String phoneNumber, String shippingAddress) {
        super(firstName, lastName, password, emailAddress, phoneNumber, shippingAddress);
    }
}
