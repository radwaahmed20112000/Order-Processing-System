package services;

import databaseAccessLayer.CustomerAccess;
import interfaces.IUser;

public class CustomerService {
    CustomerAccess customerAccess = new CustomerAccess();

    public int editProfile(IUser oldCustomer , IUser newCustomer){

        return customerAccess.editProfile(oldCustomer.getEmailAddress(),newCustomer.getFirstName(),
                newCustomer.getLastName(),newCustomer.getPassword(),newCustomer.getEmailAddress(),
                newCustomer.getPhoneNumber(),newCustomer.getShippingAddress());
    }
}
