package interfaces;

import java.util.List;

public interface IBook {

    int editBook(IBook updatedBook);

    int editBookAuthors(IBookAuthor[] UpdatedBookAuthors);

    int editBookCount(int newCount);

    boolean isEnoughCount(int count);

    int getBookId();

    String getTitle();

    IPublisher getPublisher();

    String getPublicationYear();

    float getSellingPrice();

    String getCategory();

    int getMinQuantity();

    int getCurrentQuantity();

    List<IBookAuthor> getBookAuthors();


}
