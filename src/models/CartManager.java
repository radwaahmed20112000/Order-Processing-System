package models;


import interfaces.ICart;
import services.CartService;

import java.util.List;


public class CartManager implements ICart {
    String userEmail;
    CartService cartService;

    public CartManager(String userEmail) {
        this.userEmail = userEmail;
        this.cartService = new CartService();
    }


    public boolean addToCart(int bookId , int count ){
       return cartService.addToCart(bookId,count,this.userEmail);
    }

    public void editCartBookCount(int bookId , int newCount){
       cartService.editCartBookCount(bookId,newCount,this.userEmail);
    }

    public void removeFromCart(int bookId){
        cartService.removeFromCart(bookId,this.userEmail);
    }

    public List<String> viewCartDetails(){
        return cartService.viewCartDetails(this.userEmail);
    }
    public float getCartPrice(){
       return cartService.getCartPrice(this.userEmail);
    }

    public void buyCart(){ cartService.buyCart(userEmail);}

}
