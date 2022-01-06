package controllers;

import builders.BookBuilder;
import builders.PublisherBuilder;
import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPublisher;

public class BookController {
    public IBook bookMapper(int newBookId, String newTitle, String newPublisherName,String newPublisherAddress,
                            String newPublisherTelephoneNum, String newPublicationYear, float newSellingPrice, String newCategory,
                            int newMinQuantity, int newCurrentQuantity){


        BookBuilder newBookBuilder = new BookBuilder();
        PublisherBuilder newPublisherBuilder = new PublisherBuilder();
        newBookBuilder.setBookId(newBookId);
        newBookBuilder.setTitle(newTitle);

        newPublisherBuilder.setName(newPublisherName);
        newPublisherBuilder.setAddress(newPublisherAddress);
        newPublisherBuilder.setTelephoneNumber(newPublisherTelephoneNum);
        IPublisher newPublisher = newPublisherBuilder.generatePublisher();

        newBookBuilder.setPublisher(newPublisher);
        newBookBuilder.setPublicationYear(newPublicationYear);
        newBookBuilder.setSellingPrice(newSellingPrice);
        newBookBuilder.setCategory(newCategory);
        newBookBuilder.setMinQuantity(newMinQuantity);
        newBookBuilder.setCurrentQuantity(newCurrentQuantity);
        IBook newBook = newBookBuilder.generateBook();

        return newBook;
    }
    public IBook bookIdMapper(int bookId){
        BookBuilder bookBuilder = new BookBuilder();
        bookBuilder.setBookId(bookId);
        IBook book = bookBuilder.generateBook();
        return book;
    }

//on edit book clicked
    public int editBook (int oldBookId , int newBookId, String newTitle, String newPublisherName,String newPublisherAddress,
                         String newPublisherTelephoneNum, String newPublicationYear, float newSellingPrice, String newCategory,
                         int newMinQuantity, int newCurrentQuantity) {
        //mapping data to objects
       IBook oldBook = bookIdMapper(oldBookId);
       IBook newBook = bookMapper(newBookId,  newTitle,  newPublisherName, newPublisherAddress,
                newPublisherTelephoneNum,  newPublicationYear,  newSellingPrice,  newCategory,
         newMinQuantity,  newCurrentQuantity);

        //call
        return oldBook.editBook(newBook);

    }
    //on edit authors clicked
    public int editBookAuthors(int oldBookId ,IBookAuthor[] updatedAuthors){
        //mapping input to objects
        IBook oldBook = bookIdMapper(oldBookId);
        //call
        return oldBook.editBookAuthors(updatedAuthors);

    }
}
