package controllers;

import builders.BookBuilder;
import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPublisher;

public class BookController {
    public IBook bookMapper(int newBookId, String newTitle, String newPublisherName,String newPublisherAddress,
                            String newPublisherTelephoneNum, String newPublicationYear, float newSellingPrice, String newCategory,
                            int newMinQuantity, int newCurrentQuantity,String[]bookAuthorsNames){


        BookBuilder newBookBuilder = new BookBuilder();
        newBookBuilder.setBookId(newBookId);
        newBookBuilder.setTitle(newTitle);
        newBookBuilder.setPublisher(newPublisherName,newPublisherAddress,newPublisherTelephoneNum);
        newBookBuilder.setPublicationYear(newPublicationYear);
        newBookBuilder.setSellingPrice(newSellingPrice);
        newBookBuilder.setCategory(newCategory);
        newBookBuilder.setMinQuantity(newMinQuantity);
        newBookBuilder.setCurrentQuantity(newCurrentQuantity);
        newBookBuilder.setBookAuthors(bookAuthorsNames);
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
                         int newMinQuantity, int newCurrentQuantity,String[]bookAuthorsNames) {
        //mapping data to objects
       IBook oldBook = bookIdMapper(oldBookId);
       IBook newBook = bookMapper(newBookId,  newTitle,  newPublisherName, newPublisherAddress,
                newPublisherTelephoneNum,  newPublicationYear,  newSellingPrice,  newCategory,
         newMinQuantity,  newCurrentQuantity,bookAuthorsNames);

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
