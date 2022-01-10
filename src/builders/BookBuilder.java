package builders;

import databaseAccessLayer.BookAccess;
import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPublisher;
import models.Book;
import models.BookAuthor;
import models.Publisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder Design Pattern to build book in the database
 * then to create book instance.
 */
public class BookBuilder {
    private final BookAccess access;
    private int bookId;
    private String title;
    private String publisherName;
    private String publisherAddress;
    private String publisherTelephoneNum;
    private String publicationYear;
    private float sellingPrice;
    private String category;
    private int minQuantity;
    private int currentQuantity;
    private String[] bookAuthorNames;
    private final List<IBookAuthor> bookAuthors;

    /**
     * Build Book in database and generate its object
     * @return book object
     */
    public IBook generateBookTuple(){
        if (buildPublisher() && buildBook() && buildAuthors())
            return generateBook();
        return null;
    }

    /**
     * Construct Book with its publisher and its authors
     * @return book object
     */
    public IBook generateBook() {
        IPublisher publisher = new Publisher(publisherName, publisherAddress, publisherTelephoneNum);
        if(bookAuthorNames != null){
        for (String author : bookAuthorNames)
            bookAuthors.add(new BookAuthor(author));}
        return new Book(this.bookId, this.title, publisher, this.publicationYear,
                this.sellingPrice, this.category, this.minQuantity, this.currentQuantity, this.bookAuthors);
    }

    private boolean buildBook(){
        return access.addBook(bookId, title, publisherName, publicationYear, sellingPrice, category,
                minQuantity, currentQuantity);
    }

    private boolean buildPublisher(){
        return access.addPublisher(publisherName, publisherAddress, publisherTelephoneNum);
    }

    private boolean buildAuthors() {
        for (String author : bookAuthorNames) {
            if (!access.addAuthor(bookId, author))
                return false;
        }
        return true;
    }

    public BookBuilder(){
        access = new BookAccess();
        bookAuthors = new ArrayList<>();
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public void setPublisherTelephoneNum(String publisherTelephoneNum) {
        this.publisherTelephoneNum = publisherTelephoneNum;
    }


    public void setBookAuthors(String[] bookAuthorsNames) {
        this.bookAuthorNames = bookAuthorsNames;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
