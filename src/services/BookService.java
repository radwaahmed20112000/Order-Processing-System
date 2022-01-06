package services;

import databaseAccessLayer.BookAccess;
import interfaces.IBook;
import interfaces.IBookAuthor;


public class BookService {
    BookAccess  bookAccess = new BookAccess();



    public int editBook(IBook oldBook , IBook newBook){
        return bookAccess.editBook(oldBook.getBookId(),newBook.getBookId(), newBook.getTitle(),
                newBook.getPublisher().getName(),newBook.getPublisher().getAddress(),
                newBook.getPublisher().getTelephoneNumber(),newBook.getPublicationYear(),
                newBook.getSellingPrice(),newBook.getCategory(),newBook.getMinQuantity(),newBook.getCurrentQuantity());

    }
    public int editBookAuthor(IBook oldBook , IBookAuthor[] updatedBookAuthors){
        //loop for all the authors to detect which one updated
        for(int i = 0 ; i< updatedBookAuthors.length;i++){
           if(oldBook.getBookAuthors()[i].equals(updatedBookAuthors[i]) == false){
               int res =bookAccess.editBookAuthor(oldBook.getBookId(),oldBook.getBookAuthors()[i].getName(),
                       updatedBookAuthors[i].getName());
               if (res == -1) ; return res;
           }
        }
       return 1;
    }
    public int editBookCount (int bookId , int newCount){
        //sql update query
        return -1;
    }

    public IBook findBook (String searchWords){
        bookAccess.findBook(searchWords);
        // map between what db returns and the object book
        return null;
    }

    public IBook findBookById (int bookId){
        bookAccess.findBookById(bookId);
        // map between what db returns and the object book
        return null;
    }
    public boolean isEnoughCount(int bookId ,int count){
        //select the book by the id
        //check if current quantity of the book is > count
        return false;
    }
}
