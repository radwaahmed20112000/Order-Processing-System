package databaseAccessLayer;

import interfaces.IBookAuthor;
import interfaces.IPublisher;
import models.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookAccess extends Access{
    Connection connection ;
    public BookAccess(){
        this.connection = getConnection();
    }

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
    public ResultSet findBook(String searchWords) throws SQLException {
        // select query
        //return the o/p of the sql some thing like object array
        Statement stmt = connection.createStatement();
        String query = "Select * from BOOK where isbn = '" + searchWords + "' OR title = '"
                + searchWords + "' OR publisher = '"+ searchWords + "' OR publication_year = '"+ searchWords + "' OR selling_price = '"
                + searchWords + "' OR category = '"+ searchWords + "' OR min_quantity_threshold = '"+ searchWords + "' OR current_quantity ='"+ searchWords + "'";
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    public ResultSet findBookById(int bookId) throws SQLException {
        //select query
        Statement stmt = connection.createStatement();
        String query = "Select * from BOOK where isbn = " + bookId  ;
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
   public int editBookQuantity(int bookId , int newQuantity) throws SQLException {
       Statement stmt = connection.createStatement();
       int res = stmt.executeUpdate("UPDATE  BOOK SET current_quantity = "+newQuantity +" WHERE isbn = " + bookId);
       return res;
   }

}
