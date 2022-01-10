package services;

import builders.BookBuilder;
import databaseAccessLayer.BookAccess;
import interfaces.IBook;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookService {
    BookAccess  bookAccess = new BookAccess();

    public IBook bookMapper(int newBookId, String newTitle, String newPublisherName, String newPublicationYear,
                            float newSellingPrice, String newCategory, int newMinQuantity, int newCurrentQuantity){


        BookBuilder newBookBuilder = new BookBuilder();
        newBookBuilder.setBookId(newBookId);
        newBookBuilder.setTitle(newTitle);
        newBookBuilder.setPublisherName(newPublisherName);
        newBookBuilder.setPublisherAddress("");
        newBookBuilder.setPublisherTelephoneNum("");
        newBookBuilder.setPublicationYear(newPublicationYear);
        newBookBuilder.setSellingPrice(newSellingPrice);
        newBookBuilder.setCategory(newCategory);
        newBookBuilder.setMinQuantity(newMinQuantity);
        newBookBuilder.setCurrentQuantity(newCurrentQuantity);

        return newBookBuilder.generateBook();
    }

//    public int editBook(IBook oldBook , IBook newBook){
//        return bookAccess.editBook(oldBook.getBookId(),newBook.getBookId(), newBook.getTitle(),
//                newBook.getPublisher().getName(),newBook.getPublisher().getAddress(),
//                newBook.getPublisher().getTelephoneNumber(),newBook.getPublicationYear(),
//                newBook.getSellingPrice(),newBook.getCategory(),newBook.getMinQuantity(),newBook.getCurrentQuantity());
//
//    }
//    public int editBookAuthor(IBook oldBook , IBookAuthor[] updatedBookAuthors){
//        //loop for all the authors to detect which one updated
//        for(int i = 0 ; i< updatedBookAuthors.length;i++){
//           if(oldBook.getBookAuthors()[i].equals(updatedBookAuthors[i]) == false){
//               int res =bookAccess.editBookAuthor(oldBook.getBookId(),oldBook.getBookAuthors()[i].getName(),
//                       updatedBookAuthors[i].getName());
//               if (res == -1) ; return res;
//           }
//        }
//       return 1;
//    }
    public int editBookQuantity (int bookId , int newCount){
        //sql update query
        int res;
        res = bookAccess.editBookQuantity(bookId, newCount);
        return res;
    }

    public List<IBook> findBook (String searchWords){
        // map between what db returns and the object book
        List<IBook> result  = new ArrayList<>();
        try {
           ResultSet rs = bookAccess.findBook(searchWords);
            while(rs.next()){
                IBook book = bookMapper(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getFloat(5),
                        rs.getString(6),rs.getInt(7),rs.getInt(8));
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public IBook findBookById (int bookId){
        IBook book ;
        try {
            ResultSet rs = bookAccess.findBookById(bookId);
            if (rs.next()){
                book = bookMapper(rs.getInt(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getFloat(5),
                    rs.getString(6),rs.getInt(7),rs.getInt(8));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // map between what db returns and the object book
        return null;
    }
    public boolean isEnoughCount(int bookId ,int count){
        //select the book by the id
         IBook book = findBookById(bookId);
        //check if current quantity of the book is > count
        return (book.getCurrentQuantity() >= count);
    }
}
