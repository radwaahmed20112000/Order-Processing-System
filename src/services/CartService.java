package services;

import builders.BookBuilder;
import databaseAccessLayer.CartAccess;
import databaseAccessLayer.OrderAccess;
import interfaces.IBook;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CartService {
    CartAccess cartAccess;
    OrderAccess orderAccess;
    BookService bookService;
    public CartService(){
    cartAccess = new CartAccess();
    bookService = new BookService();
    orderAccess = new OrderAccess();
    }
   public IBook bookIdMapper(int bookId){
       BookBuilder bookBuilder = new BookBuilder();
       bookBuilder.setBookId(bookId);
       return bookBuilder.generateBook();
   }

    public boolean addToCart(int bookId , int count ,String email){
       IBook book = bookIdMapper(bookId);
       //check if there is enough currentQuantity of this book
        if (book.isEnoughCount(count)){
            // if there is then update it -- from the current Quantity done in editQuantity function
            IBook savedBook = bookService.findBookById(bookId);
            book.editBookCount(savedBook.getCurrentQuantity() - count);
            try {
                // add the book with the desired count in user cart
                cartAccess.addToCart(bookId,count,email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            //show message there is no enough quantity
            return false;
        }
    }
    public void editCartBookCount(int bookId , int newCount ,String email){
        //first remove the book from the cart to release the copies hold
        removeFromCart(bookId,email);
        //now the table book is updated
        //add the book to the cart again
       addToCart(bookId,newCount,email);
    }

    public void removeFromCart(int bookId , String email){
        IBook book = bookIdMapper(bookId);
        //get the count of copies of the book in the cart
         int count = getBookCountCart( bookId ,email);
         //get the currentQuantity of the book to add the released ones to it
         IBook savedBook = bookService.findBookById(bookId);
         // return the book copies on the shelf
         book.editBookCount(savedBook.getCurrentQuantity() + count);

        try {
            //remove the item from the cart
            cartAccess.removeFromCart(bookId,email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getBookCountCart (int bookId , String email) {
        int count = 0;
        try {
            ResultSet rs = cartAccess.getBookCountCart(bookId, email);
            while(rs.next()){count = rs.getInt(1);}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<String> viewCart(String email){
        List<String>res = new ArrayList<>();
        try {
            ResultSet rs = cartAccess.viewCart(email);
            while(rs.next()){
                String s = rs.getInt(2)+":"+rs.getInt(3);
                res.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
    public void buyCart(String email) {
        try {
            orderAccess.addOrder(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String> cartItems = viewCart(email);
        for (String Item : cartItems) {
            int bookId = Integer.parseInt(Item.substring(0, Item.indexOf(':')));
            int count = Integer.parseInt(Item.substring(Item.indexOf(':') + 1, Item.length()));
            try {
                orderAccess.addOrderItem(email,bookId,count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public float getCartPrice(String email){
        List<String> cartItems = viewCart(email);
        float price = 0;
        for(String Item : cartItems){
            int bookId = Integer.parseInt(Item.substring(0,Item.indexOf(':')));
            int count = Integer.parseInt(Item.substring(Item.indexOf(':')+1,Item.length()));
            IBook book = bookService.findBookById(bookId);
            price += book.getSellingPrice()* count;
        }
        return price;
    }
    public List<String> viewCartDetails(String email){
        List<String> cartItems = viewCart(email);
        List<String> result = new ArrayList<>();
        for(String Item : cartItems){
            int bookId = Integer.parseInt(Item.substring(0,Item.indexOf(':')));
            int count = Integer.parseInt(Item.substring(Item.indexOf(':')+1,Item.length()));
            IBook book = bookService.findBookById(bookId);
           String temp = bookId+":" +book.getTitle()+":"+book.getSellingPrice()+
                   ":"+count;
           result.add(temp);
        }
        return result;
    }
}
