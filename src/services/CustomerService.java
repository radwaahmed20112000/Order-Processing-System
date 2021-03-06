package services;

import databaseAccessLayer.CustomerAccess;
import interfaces.IUser;

import java.sql.SQLException;

public class CustomerService {
    CustomerAccess customerAccess = new CustomerAccess();
    public static IUser currentUser;

    public boolean signUp(String firstName,
                          String lastName,
                          String password,
                          String emailAddress,
                          String phoneNumber,
                          String shippingAddress,
                          boolean userType,String userName) {
        IUser created = new IUser(firstName,lastName,password,emailAddress,phoneNumber,shippingAddress,userName);
        // check if user can be added successfully
        return customerAccess.addUser(created, userType);
        // if user added successfully, acknowledge user and ask user to login
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

    public int editProfile(IUser oldCustomer , IUser newCustomer)  {

        int res = -1;
        try {
            res = customerAccess.editProfile(oldCustomer.getEmailAddress(),newCustomer.getFirstName(),
                    newCustomer.getLastName(),newCustomer.getPassword(),newCustomer.getEmailAddress(),
                    newCustomer.getPhoneNumber(),newCustomer.getShippingAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void logOut (String email){
        try {
             customerAccess.logOut(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
