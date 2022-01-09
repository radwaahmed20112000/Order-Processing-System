package controllers;

import interfaces.IUser;

public class CartController {
    public IUser userEmailMapper(String email){
        IUser user = new IUser(email);
        return user;
    }
    //on add to cart clicked
    public void addToCart(int bookId , int count , String email){
        userEmailMapper(email).getCart().addToCart(bookId,count);
    }
    //on edit quantity button clicked
   public void editQuantity(int bookId , int newCount,String email){
       userEmailMapper(email).getCart().editQuantity(bookId,newCount);
   }
   //on remove from cart button clicked
    public void removeFromCart(int bookId ,String email){
        userEmailMapper(email).getCart().removeFromCart(bookId);
    }
    //on view cart clicked
    public Object[] viewCart(String email){
        return userEmailMapper(email).getCart().viewCart();
    }
    //on get cart price clicked
    public float getCartPrice(String email){
        return userEmailMapper(email).getCart().getCartPrice();
    }
}
