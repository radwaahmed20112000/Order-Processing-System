package controllers;

import interfaces.IUser;

public class CustomerController {
    public IUser userMapper (String newUserFirstName , String newUserLastName , String newUserPassword
            ,String newUserEmail , String newUserPhoneNum , String newUserShippingAddress){
        IUser newUser = new IUser(newUserFirstName,newUserLastName,newUserPassword,newUserEmail,newUserPhoneNum
                ,newUserShippingAddress);
        return newUser;
    }
    public IUser userEmailMapper (String email){
        IUser user = new IUser(email);
        return user;
    }
    //on edit button clicked
    public void editProfile(String oldUserEmail , String newUserFirstName , String newUserLastName , String newUserPassword
            ,String newUserEmail , String newUserPhoneNum , String newUserShippingAddress){

        IUser oldUSer = userEmailMapper(oldUserEmail);
        IUser newUser = userMapper(newUserFirstName ,  newUserLastName ,  newUserPassword
                , newUserEmail ,  newUserPhoneNum ,  newUserShippingAddress);

         oldUSer.editProfile(newUser);
    }

}
