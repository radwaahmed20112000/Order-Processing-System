package controllers;

import builders.UserBuilder;
import interfaces.IUser;

public class CustomerController {
    public IUser userMapper (String newUserFirstName , String newUserLastName , String newUserPassword
            ,String newUserEmail , String newUserPhoneNum , String newUserShippingAddress){
        UserBuilder newUserBuilder = new UserBuilder();
        newUserBuilder.setFirstName(newUserFirstName);
        newUserBuilder.setLastName(newUserLastName);
        newUserBuilder.setEmailAddress(newUserEmail);
        newUserBuilder.setPassword(newUserPassword);
        newUserBuilder.setPhoneNumber(newUserPhoneNum);
        newUserBuilder.setShippingAddress(newUserShippingAddress);
        IUser newUser = newUserBuilder.generateUser();
        return newUser;
    }
    public IUser userEmailMapper (String email){
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setEmailAddress(email);
        IUser user = userBuilder.generateUser();
        return user;
    }
    //on edit button clicked
    public int editProfile(String oldUserEmail , String newUserFirstName , String newUserLastName , String newUserPassword
            ,String newUserEmail , String newUserPhoneNum , String newUserShippingAddress){

        IUser oldUSer = userEmailMapper(oldUserEmail);
        IUser newUser = userMapper(newUserFirstName ,  newUserLastName ,  newUserPassword
                , newUserEmail ,  newUserPhoneNum ,  newUserShippingAddress);

        return oldUSer.editProfile(newUser);
    }

}
