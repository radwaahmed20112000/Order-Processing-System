package builders;

import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPublisher;
import models.Book;

public class BookBuilder {
    int bookId;
    String title;
    IPublisher publisher;
    String publicationYear;
    float sellingPrice;
    String category;
    int minQuantity;
    int currentQuantity;
    IBookAuthor[] bookAuthors;

    public void setBookAuthors(IBookAuthor[] bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(IPublisher publisher) {
        this.publisher = publisher;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public IBook generateBook(){
        IBook book = new Book( this. bookId, this.title, this. publisher, this. publicationYear,
        this. sellingPrice, this. category, this. minQuantity, this. currentQuantity,this.bookAuthors);
        return book;
    }
}