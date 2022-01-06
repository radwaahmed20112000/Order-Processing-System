package interfaces;


public interface ICart {

    void addToCart(int bookId , int count);
    void editQuantity(int bookId , int newCount);
    void removeFromCart(int bookId);
    Object[] viewCart();
    float getCartPrice();

}
