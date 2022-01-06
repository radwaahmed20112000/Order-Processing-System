package builders;

import interfaces.IUser;

public class UserBuilder {
    String firstName;
    String lastName;
    String password;
    String emailAddress;
    String phoneNumber;
    String shippingAddress;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public IUser generateUser (){
        IUser user = new IUser(this.firstName,this.lastName,this.password,this.emailAddress,
                this.phoneNumber,this.shippingAddress);
        return user;
    }
}
