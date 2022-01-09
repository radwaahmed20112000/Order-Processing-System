package services;

import databaseAccessLayer.CustomerAccess;
import interfaces.IUser;

public class CustomerService {
    CustomerAccess customerAccess = new CustomerAccess();
    IUser currentUser;

    public boolean signUp(String firstName,
                          String lastName,
                          String password,
                          String emailAddress,
                          String phoneNumber,
                          String shippingAddress,
                          boolean userType) {
        IUser created = new IUser(firstName,lastName,password,emailAddress,phoneNumber,shippingAddress);
        // check if user can be added successfully
        if(!customerAccess.addUser(created, userType))
            return false;
        // if user added successfully, acknowledge user and ask user to login
        return true;
    }

    public boolean signIn(String emailAddress, String password, boolean type) {
        // if login failed it may be wrong username, password, both or user already logged in
        if((currentUser = customerAccess.signIn(emailAddress, password, type)) != null)
            return true;
        // if no such user
        return false;
    }

    public void signOut() {
        currentUser = null;
    }

    public int editProfile(IUser oldCustomer , IUser newCustomer){

        return customerAccess.editProfile(oldCustomer.getEmailAddress(),newCustomer.getFirstName(),
                newCustomer.getLastName(),newCustomer.getPassword(),newCustomer.getEmailAddress(),
                newCustomer.getPhoneNumber(),newCustomer.getShippingAddress());
    }
}
