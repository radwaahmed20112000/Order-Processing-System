package models;

import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPublisher;
import services.BookService;

import java.util.List;

public class Book implements IBook {
    private final int bookId;
    private final String title;
    private final IPublisher publisher;
    private final String publicationYear;
    private final float sellingPrice;
    private final String category;
    private final int minQuantity;
    private final int currentQuantity;
    private final List<IBookAuthor> bookAuthors;
    private final BookService bookService ;

    public Book(int bookId, String title, IPublisher publisher,
                String publicationYear, float sellingPrice, String category,
                int minQuantity, int currentQuantity, List<IBookAuthor> bookAuthors) {
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.sellingPrice = sellingPrice;
        this.category = category;
        this.minQuantity = minQuantity;
        this.currentQuantity = currentQuantity;
        this.bookAuthors = bookAuthors;
        this.bookService = new BookService();
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public IPublisher getPublisher() {
        return publisher;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public String getCategory() {
        return category;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public List<IBookAuthor> getBookAuthors() {
        return bookAuthors;
    }


    @Override
    public int editBook(IBook updatedBook) {
        //TODO
//        return bookService.editBook(this,updatedBook);
        return 0;
    }

    public int editBookAuthors(IBookAuthor[] updatedBookAuthors){
        //TODO
//        return bookService.editBookAuthor(this, updatedBookAuthors);
        return 0;
    }

    @Override
    public int editBookCount(int newCount) {
        return bookService.editBookQuantity(this.bookId, newCount);
    }


    public boolean isEnoughCount(int count){
        return bookService.isEnoughCount(this.bookId,count);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", publisher=" + publisher +
                ", publicationYear='" + publicationYear + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", category='" + category + '\'' +
                ", minQuantity=" + minQuantity +
                ", currentQuantity=" + currentQuantity +
                //TODO FARIDA
//                ", bookAuthors=" + Arrays.toString(bookAuthors) +
                '}';
    }
}
