package services;

import builders.BookBuilder;
import databaseAccessLayer.BookAccess;
import interfaces.IBook;
import interfaces.IBookAuthor;


import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookService {
    BookAccess  bookAccess = new BookAccess();

    public IBook bookMapper(int newBookId, String newTitle, String newPublisherName, String newPublicationYear,
                            float newSellingPrice, String newCategory, int newMinQuantity, int newCurrentQuantity,
                            String publisherAddress, String publisherPhone, String[] authors){


        BookBuilder newBookBuilder = new BookBuilder();
        newBookBuilder.setBookId(newBookId);
        newBookBuilder.setTitle(newTitle);
        newBookBuilder.setPublisherName(newPublisherName);
        newBookBuilder.setPublisherAddress(publisherAddress);
        newBookBuilder.setPublisherTelephoneNum(publisherPhone);
        newBookBuilder.setPublicationYear(newPublicationYear);
        newBookBuilder.setSellingPrice(newSellingPrice);
        newBookBuilder.setCategory(newCategory);
        newBookBuilder.setMinQuantity(newMinQuantity);
        newBookBuilder.setCurrentQuantity(newCurrentQuantity);
        newBookBuilder.setBookAuthors(authors);

        return newBookBuilder.generateBook();
    }

    public boolean editBook(IBook oldBook , IBook newBook){
        return bookAccess.editBook(oldBook.getBookId(), newBook.getBookId(), newBook.getTitle(),
                newBook.getPublisher().getName(), newBook.getPublicationYear(),
                newBook.getSellingPrice(), newBook.getCategory(), newBook.getMinQuantity(),
                newBook.getCurrentQuantity())
                && bookAccess.editPublisher(oldBook.getPublisher().getName(), newBook.getPublisher().getName(),
                newBook.getPublisher().getAddress(), newBook.getPublisher().getTelephoneNumber())
                && editBookAuthor(oldBook, newBook);
    }
    public boolean editBookAuthor(IBook oldBook , IBook newBook){
        List<IBookAuthor> union = new ArrayList<>(oldBook.getBookAuthors());
        union.addAll(newBook.getBookAuthors());
        List<IBookAuthor> intersection = new ArrayList<>(oldBook.getBookAuthors());
        intersection.retainAll(newBook.getBookAuthors());
        union.removeAll(intersection);

        boolean edit = true;
        for (IBookAuthor author : union)
            edit = edit && bookAccess.addAuthor(newBook.getBookId(), author.getName());
       return edit;
    }
    public IBook findBookDetails(int bookId) {
        try {
            ResultSet authors = bookAccess.findBookAuthors(bookId);
            List<String> bookAuthors = new ArrayList<>();
            while (authors.next()) {
                bookAuthors.add(authors.getString(1));
            }
            ResultSet rs = bookAccess.findBookDetailsById(bookId);
            rs.next();
            return bookMapper(rs.getInt(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getFloat(5),
                    rs.getString(6),rs.getInt(7),rs.getInt(8),
                    rs.getString(10), rs.getString(11), bookAuthors.toArray(new String[0]));
        } catch (Exception ignored){
            ignored.printStackTrace();
        }
        return null;
    }
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
                        rs.getString(6),rs.getInt(7),rs.getInt(8),"",
                        "", new String[]{});
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public List<IBook> getBooks () {
        List<IBook> result  = new ArrayList<>();
        try {
            ResultSet rs = bookAccess.showBook();
            while(rs.next()){
                IBook book = bookMapper(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getFloat(5),
                        rs.getString(6),rs.getInt(7),rs.getInt(8),"",
                        "", new String[]{});
                ResultSet authorRs = bookAccess.findBookAuthors(rs.getInt(1));

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
                    rs.getString(6),rs.getInt(7),rs.getInt(8), "", "",
                        new String[]{});
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
