package databaseAccessLayer;

import java.sql.Connection;
import java.sql.Statement;

public class BookAccess extends Access {

    Connection connection;
    Statement stmt;

    public BookAccess() {
        connection = getConnection();
        try {
            stmt = connection.createStatement();
        }catch (Exception ignore){}
    }

    public boolean addBook(int isbn, String title, String publisherName,
                           String publicationYear, float sellingPrice, String category,
                           int minQuantity, int currentQuantity){
        try {
            stmt.executeUpdate("INSERT INTO BOOK VALUES('" + isbn + "','" + title + "','" +
                    publisherName + "','" + publicationYear + "','" + sellingPrice + "','" + category +
                    "','" + minQuantity + "','" + currentQuantity + "')");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean addPublisher(String publisherName, String publisherAddress, String publisherTelephoneNum){
        try {
            stmt.executeUpdate("INSERT INTO PUBLISHER VALUES('" + publisherName + "','" +
                    publisherAddress + "','" + publisherTelephoneNum + "')");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean addAuthor(int bookId, String authorName){
        try {
            stmt.executeUpdate("INSERT INTO BOOK_AUTHOR VALUES('" + bookId + "','" + authorName + "')");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean editBook (int oldBookId , int bookId, String title, String publisherName, String publicationYear,
                             float sellingPrice, String category, int minQuantity, int currentQuantity){
        try {
            stmt.executeUpdate("UPDATE BOOK " +
                    "SET isbn='" + bookId +
                    "', title='" + title +
                    "', publisher='" + publisherName +
                    "', publication_year='" + publicationYear +
                    "', selling_price='" + sellingPrice +
                    "', category='" + category +
                    "', min_quantity_threshold='" + minQuantity +
                    "', current_quantity='" + currentQuantity +
                    "' WHERE isbn ='" + oldBookId + "'");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean editPublisher(String oldPublisher, String publisherName, String publisherAddress, String publisherTelephoneNum){
        try {
            stmt.executeUpdate("UPDATE PUBLISHER " +
                    "SET name='" + publisherName +
                    "', address='" + publisherAddress +
                    "', phone_number='" + publisherTelephoneNum +
                    "' WHERE name ='" + oldPublisher + "'");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean editBookAuthor(int bookId, String oldAuthorName, String newAuthorName){
        try {
            stmt.executeUpdate("UPDATE BOOK_AUTHOR " +
                    "SET name='" + newAuthorName +
                    "' WHERE book_id ='" + bookId +
                    "' AND name='" + oldAuthorName + "'");
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
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
