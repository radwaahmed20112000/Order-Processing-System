package models;

import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPublisher;
import services.BookService;

public class Book implements IBook {
    int bookId;
    String title;
    IPublisher publisher;
    String publicationYear;
    float sellingPrice;
    String category;
    int minQuantity;
    int currentQuantity;
    IBookAuthor[] bookAuthors;

    BookService bookService ;

    public Book(int bookId, String title, IPublisher publisher,
                String publicationYear, float sellingPrice, String category,
                int minQuantity, int currentQuantity,IBookAuthor[] bookAuthors) {
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

    public IBookAuthor[] getBookAuthors() {
        return bookAuthors;
    }


    @Override
    public int editBook(IBook updatedBook) {
        return bookService.editBook(this,updatedBook);
    }
    public int editBookAuthors(IBookAuthor[] updatedBookAuthors){
        return bookService.editBookAuthor(this, updatedBookAuthors);
    }
    public int editBookCount (int newCount){
        return bookService.editBookCount(this.bookId,newCount);
    }
    public boolean isEnoughCount(int count){
        return bookService.isEnoughCount(this.bookId,count);
    }
}
