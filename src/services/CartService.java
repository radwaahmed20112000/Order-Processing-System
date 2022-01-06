package services;

import builders.BookBuilder;
import databaseAccessLayer.CartAccess;
import interfaces.IBook;


public class CartService {
   CartAccess cartAccess = new CartAccess();

   public IBook bookIdMapper(int bookId){
       BookBuilder bookBuilder = new BookBuilder();
       bookBuilder.setBookId(bookId);
       IBook book = bookBuilder.generateBook();
       return book;
   }

    public void addToCart(int bookId , int count ,String email){
       IBook book = bookIdMapper(bookId);
        boolean valid = book.isEnoughCount(count);
        if (valid == true){
            book.editBookCount(count);
            cartAccess.addToCart(bookId,count,email);
        }else{
            //show message there is no enough quantity
        }
    }
    public void editQuantity(int bookId , int newCount ,String email){
        IBook book = bookIdMapper(bookId);
        boolean valid = book.isEnoughCount(newCount);
        if (valid == true){
            book.editBookCount(newCount);
            cartAccess.addToCart(bookId,newCount,email);
        }else{
            //show message there is no enough quantity
        }
    }

    public void removeFromCart(int bookId , String email){
        IBook book = bookIdMapper(bookId);
         int count = cartAccess.getBookCountCart( bookId ,email);
         book.editBookCount(count);
         cartAccess.removeFromCart(bookId,email);
    }
    public Object[] viewCart(String email){
        cartAccess.viewCart(email);
        return null;
    }
    public float getCartPrice(String email){
       return cartAccess.getCartPrice(email);
    }
    public int getBookCountCart (int bookId , String email) {
       return cartAccess.getBookCountCart(bookId, email);
    }
}
