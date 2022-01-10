package interfaces;


import java.util.List;

public interface ICart {

    boolean addToCart(int bookId, int count);

    void editCartBookCount(int bookId, int newCount);

    void removeFromCart(int bookId);

    List<String> viewCart();

    float getCartPrice();

}
