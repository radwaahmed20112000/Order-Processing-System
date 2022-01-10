package models;

import databaseAccessLayer.BookAccess;
import databaseAccessLayer.ManagerAccess;
import interfaces.IUser;

public class Manager extends IUser {
    ManagerAccess managerAccess = new ManagerAccess();
    BookAccess bookAccess = new BookAccess();

    public Manager(String firstName, String lastName, String password, String emailAddress, String phoneNumber, String shippingAddress,String userName) {
        super(firstName, lastName, password, emailAddress, phoneNumber, shippingAddress,userName);
    }
    public void confirmOrder (){

    }
    public void addBook(){

    }
    public void editBook(){

    }
    public void placeOrder(){

    }
    public void viewAllCustomers(){

    }
    public void promoteUsers(){

    }
    public void viewTotalSalesInMonth(){

    }
    public void topFiveCustomers(){

    }
    //in last 3 months
    public void topSellingBooks(){

    }
}
