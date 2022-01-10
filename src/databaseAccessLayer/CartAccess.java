package databaseAccessLayer;

import interfaces.IBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Set;

public class CartAccess extends Access {
    Connection connection;
    public CartAccess (){
        connection = getConnection();
    }
    public void addToCart(int bookId , int count ,String email) throws SQLException {
        //sql query insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("insert into CART values('" + email + "','" + bookId + "','" + count  + "')");
    }

    public void removeFromCart(int bookId , String email) throws SQLException {
        //sql query delete
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("delete from CART  where email = '" + email +"' AND bookId = "+ bookId );
    }

    public ResultSet viewCart(String email){
       //sql query select * from table cart
        return null;
    }
    public float getCartPrice(String email){
    //nested sql query to get the price
        return -1;
    }
    public ResultSet getBookCountCart( int bookId ,String email ) throws SQLException {
        //select query
        Statement stmt = connection.createStatement();
        String query = "Select count from cart where email = '" + email +"' AND bookId = "+ bookId  ;
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
}
