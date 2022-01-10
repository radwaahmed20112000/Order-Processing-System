package databaseAccessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;


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
        try {
            stmt.executeUpdate("INSERT INTO SUPPLIER_ORDER values('" + bookId + "','" + requiredQuantity + "','" +
                    Timestamp.valueOf(LocalDateTime.now()) + "')");
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
            return stmt.executeQuery("SELECT * FROM USER");
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

    /**
     *
     * @return The total sales for books in the previous month
     */
    public ResultSet viewTotalSalesInMonth(){
        try {
            return stmt.executeQuery("SELECT * FROM CLIENT_ORDER, CLIENT_ORDER_DETAILS " +
                    "WHERE CLIENT_ORDER_DETAILS.order_id=CLIENT_ORDER.order_id AND " +
                    "CLIENT_ORDER.order_date BETWEEN SUBDATE(CURDATE(), INTERVAL 1 MONTH) AND NOW()" );
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @return The top 5 customers who purchase the most purchase
     * amount in descending order for the last three months
     */

    public ResultSet topFiveCustomers(){
        try {
            return stmt.executeQuery("SELECT SUM(CLIENT_ORDER_DETAILS.order_count) as count, user_email " +
                    "FROM CLIENT_ORDER, CLIENT_ORDER_DETAILS " +
                    "WHERE CLIENT_ORDER_DETAILS.order_id=CLIENT_ORDER.order_id AND " +
                    "CLIENT_ORDER.order_date BETWEEN SUBDATE(CURDATE(), INTERVAL 3 MONTH) AND NOW() " +
                    "GROUP BY user_email " +
                    "order by count DESC " +
                    "LIMIT 5");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @return top 10 selling books for the last three months
     */
    public ResultSet topSellingBooks() {
        try {
            return stmt.executeQuery("SELECT SUM(CLIENT_ORDER_DETAILS.order_count) as count, " +
                    "title FROM CLIENT_ORDER, CLIENT_ORDER_DETAILS, BOOK " +
                    "FROM CLIENT_ORDER, CLIENT_ORDER_DETAILS " +
                    "WHERE CLIENT_ORDER_DETAILS.order_id=CLIENT_ORDER.order_id AND " +
                    "BOOK.isbn = CLIENT_ORDER_DETAILS.book_id AND " +
                    "CLIENT_ORDER.order_date BETWEEN SUBDATE(CURDATE(), INTERVAL 3 MONTH) AND NOW() " +
                    "GROUP BY book_id " +
                    "order by count DESC " +
                    "LIMIT 10");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
