package databaseAccessLayer;

import interfaces.IBook;

import java.util.Hashtable;
import java.util.Set;

public class CartAccess {
    public void addToCart(int bookId , int count ,String email){
        //sql query insert
    }
    public void editQuantity(int bookId , int newCount , String email){
       // sql query update
    }
    public void removeFromCart(int bookId , String email){
        //sql query delete
    }
    public Object[] viewCart(String email){
       //sql query select * from table cart
        return null;
    }
    public float getCartPrice(String email){
    //nested sql query to get the price
        return -1;
    }
    public int getBookCountCart( int bookId ,String email ){
        //select query
        return -1;
    }
}
