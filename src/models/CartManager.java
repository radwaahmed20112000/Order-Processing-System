package models;

import interfaces.IBook;
import interfaces.ICart;
import services.CartService;


public class CartManager implements ICart {
    String userEmail;
    CartService cartService;

    public CartManager(String userEmail) {
        this.userEmail = userEmail;
        this.cartService = new CartService();
    }


    public void addToCart(int bookId , int count ){
       cartService.addToCart(bookId,count,this.userEmail);
    }
    public void editQuantity(int bookId , int newCount){
       cartService.editQuantity(bookId,newCount,this.userEmail);
    }
    public void removeFromCart(int bookId){
        cartService.removeFromCart(bookId,this.userEmail);
    }
    public Object[] viewCart(){
        return cartService.viewCart(this.userEmail);
    }
    public float getCartPrice(){
       return cartService.getCartPrice(this.userEmail);
    }

}
