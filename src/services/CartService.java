package services;

import builders.BookBuilder;
import databaseAccessLayer.CartAccess;
import interfaces.IBook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CartService {
    CartAccess cartAccess;
    BookService bookService;
    public CartService(){
    cartAccess = new CartAccess();
    bookService = new BookService();
    }
   public IBook bookIdMapper(int bookId){
       BookBuilder bookBuilder = new BookBuilder();
       bookBuilder.setBookId(bookId);
       IBook book = bookBuilder.generateBook();
       return book;
   }

    public boolean addToCart(int bookId , int count ,String email){
       IBook book = bookIdMapper(bookId);
       //check if there is enough currentQuantity of this book
        boolean valid = book.isEnoughCount(count);
        if (valid == true){
            // if there is then update it -- from the current Quantity done in editQuantity function
            IBook savedBook = bookService.findBookById(bookId);
            book.editBookQuantity(savedBook.getCurrentQuantity()-count);
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
         IBook savedBook =bookService.findBookById(bookId);
         // return the book copies on the shelf
         book.editBookQuantity(book.getCurrentQuantity()+count);
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
        cartAccess.viewCart(email);
        return null;
    }
    public float getCartPrice(String email){
        return cartAccess.getCartPrice(email);
    }
}
