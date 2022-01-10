package interfaces;

public interface IBook {

 int editBook(IBook updatedBook);
 int editBookAuthors(IBookAuthor[] UpdatedBookAuthors);
  int editBookQuantity (int newQuantity);

  boolean isEnoughCount(int count);

 int getBookId() ;
 String getTitle();
  IPublisher getPublisher();
  String getPublicationYear() ;
  float getSellingPrice() ;
  String getCategory();
  int getMinQuantity() ;
  int getCurrentQuantity() ;
 IBookAuthor[] getBookAuthors() ;
  String toString();

}
