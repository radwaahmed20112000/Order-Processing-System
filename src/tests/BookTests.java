package tests;

import builders.BookBuilder;
import databaseAccessLayer.ManagerAccess;
import interfaces.IBook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookTests {
    @Test
    public void addBookTest()
    {
        BookBuilder bookBuilder = new BookBuilder();
        bookBuilder.setBookId(123);
        bookBuilder.setPublisherName("Radwa Ahmed");
        String[] authors = {"Sarah", "Shaza"};
        bookBuilder.setBookAuthors(authors);
        bookBuilder.setPublisherAddress("Alex");
        bookBuilder.setPublisherTelephoneNum("0123");
        bookBuilder.setCategory("Comedy");
        bookBuilder.setMinQuantity(5);
        bookBuilder.setCurrentQuantity(50);
        bookBuilder.setSellingPrice(100);
        bookBuilder.setPublicationYear("2012");
        bookBuilder.setTitle("I love my baby");
        IBook book = bookBuilder.generateBookTuple();
        assertEquals(book.getBookId(),123);
    }
    @Test
    public void placeOrderTest()
    {
        ManagerAccess access = new ManagerAccess();
        //access.placeOrder(123, 3);
        assertTrue(access.confirmOrder(123));
    }
}