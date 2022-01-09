package services;

import builders.UserBuilder;
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
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setFirstName(firstName);
        userBuilder.setLastName(lastName);
        userBuilder.setPassword(password);
        userBuilder.setEmailAddress(emailAddress);
        userBuilder.setPhoneNumber(phoneNumber);
        userBuilder.setShippingAddress(shippingAddress);
        IUser created = userBuilder.generateUser();
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
