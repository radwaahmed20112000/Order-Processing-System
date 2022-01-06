package databaseAccessLayer;

import interfaces.IBookAuthor;
import interfaces.IPublisher;

public class BookAccess {

    public int editBook (int oldBookId , int bookId, String title, String publisherName,String publisherAddress,
                        String publisherTelephoneNum, String publicationYear, float sellingPrice, String category,
                         int minQuantity, int currentQuantity){
        //sql update query
        return -1;
    }
    public int editBookAuthor(int oldBookId , String oldAuthorName , String newAuthorName){
        //sql update query to the table book author;
        return -1;
    }
    public Object[] findBook(String searchWords){
        // select query
        //return the o/p of the sql some thing like object array
        return null;
    }
    public Object findBookById(int bookId){
        //select query
        return null;
    }

}
