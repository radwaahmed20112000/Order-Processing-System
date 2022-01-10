package interfaces;


import models.CartManager;
import services.BookService;
import services.CustomerService;

import java.sql.SQLException;
import java.util.List;

public class IUser {

    String firstName;
    String lastName;
    String password;
    String emailAddress;
    String phoneNumber;
    String shippingAddress;
    ICart cart;
    CustomerService customerService;
    BookService bookService;


    public IUser (String email){
        this.emailAddress = email;
        this.cart = new CartManager(this.emailAddress);
        this.customerService = new CustomerService();
        this.bookService = new BookService();
    }
    public IUser(String firstName, String lastName, String password, String emailAddress, String phoneNumber,
                 String shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.shippingAddress = shippingAddress;
        this.cart = new CartManager(this.emailAddress);
        this.customerService = new CustomerService();
        this.bookService = new BookService();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public ICart getCart() {
        return cart;
    }

    public int editProfile(IUser updatedUser){

            int res =  customerService.editProfile(this,updatedUser);
            return res;

    }
    public List<IBook> searchBook(String searchWords){
        List<IBook> res =  bookService.findBook(searchWords);
        for(int i = 0 ;i<res.size();i++){
            System.out.println(res.get(i).toString());
        }
        return res;
       // return  bookService.findBook(searchWords);
    }



}
