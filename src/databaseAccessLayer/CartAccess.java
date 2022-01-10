package databaseAccessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CartAccess extends Access {
    Connection connection;
    Statement stmt;

    public CartAccess (){
        connection = getConnection();
    }

    public void addToCart(int bookId , int count ,String email) throws SQLException {
        //sql query insert
        stmt = connection.createStatement();
        stmt.executeUpdate("insert into CART values('" + email + "','" + bookId + "','" + count  + "')");
    }

    public void removeFromCart(int bookId , String email) throws SQLException {
        //sql query delete
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("delete from CART  where user_email = '" + email +"' AND book_id = "+ bookId );
    }

    public ResultSet viewCart(String email) throws SQLException {
       //sql query select * from table cart
        Statement stmt = connection.createStatement();
        String query = "Select * from cart where user_email = '" + email +"'"  ;
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }


    public ResultSet getBookCountCart( int bookId ,String email ) throws SQLException {
        //select query
        Statement stmt = connection.createStatement();
        String query = "Select count from cart where user_email = '" + email +"' AND book_id = "+ bookId  ;
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
}
