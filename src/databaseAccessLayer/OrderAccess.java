package databaseAccessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderAccess extends Access{
    Connection connection;
    Statement stmt;
    String orderTime;
    public OrderAccess(){
        this.connection = getConnection();
    }

    public void addOrder(String email ) throws SQLException {
        stmt = connection.createStatement();
        String timeQuery = "select NOW()"  ;
        ResultSet rs = stmt.executeQuery(timeQuery);
        rs.next();
        orderTime = rs.getTimestamp(1).toString();
        //TODO change to time stamp remove the single coats
        String query = "insert into client_order (user_email , order_date) values('" + email + "' ," +orderTime+ ")"  ;
        stmt.executeUpdate(query);
    }
    public int getOrderId(String email) throws SQLException {
        String query = "select order_id from client_order where user_email = '"+email+"' AND order_date = '"+ orderTime +"'"  ;
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
       return rs.getInt(1);
    }
    public void addOrderItem(String email ,int bookId ,int count) throws SQLException {
        String query = "insert into client_order_details values('" + getOrderId(email) + "','" + bookId + "','" + count  + "')";
        stmt.executeUpdate(query);
    }
}
