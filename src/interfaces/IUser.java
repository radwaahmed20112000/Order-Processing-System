package interfaces;


import models.CartManager;
import services.BookService;
import services.CustomerService;

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
        return customerService.editProfile(this,updatedUser);
    }
    public IBook searchBook(String searchWords){
        return bookService.findBook(searchWords);
    }



}
