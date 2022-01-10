package databaseAccessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class ManagerAccess extends Access{

    Connection connection;
    Statement stmt;

    public ManagerAccess() {
        connection = getConnection();
        try {
            stmt = connection.createStatement();
        }catch (Exception ignore){}
    }

    public String placeOrder(int bookId, int requiredQuantity){
        long millis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(millis);
        BookAccess bookAccess = new BookAccess();
        try {
            //TODO : UPDATE Book Quantity
            stmt.executeUpdate("INSERT INTO SUPPLIER_ORDER values('" + bookId + "','" + requiredQuantity + "','" +
                    currentDate + "')");
            return "Order is Placed Successfully";
        }catch(Exception e) {
            return e.getMessage();
        }
    }
    public boolean confirmOrder (int bookId){
        try {
            stmt.executeUpdate("DELETE FROM SUPPLIER_ORDER WHERE book_id='" + bookId + "'");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ResultSet viewAllCustomers(){
        try {
            return stmt.executeQuery("SELECT * FROM CUSTOMER");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public boolean promoteUsers(String email){
        try {
            stmt.executeUpdate("INSERT INTO MANAGER VALUES('" + email + "')");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void viewTotalSalesInMonth(){

    }
    public void topFiveCustomers(){

    }
    //in last 3 months
    public void topSellingBooks(){

    }
}
