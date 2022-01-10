package GUI;

import builders.BookBuilder;
import interfaces.IBook;
import services.BookService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO : add book, edit book
public class BookCreation extends JFrame implements ActionListener {
    BookService bookService = new BookService();
    private final JTextField ttitle;
    private final JTextField tpublicationyear;
    private final JTextField tprice;
    private final JTextField tpublisherphone;
    private final JTextField tcurrQuantity;
    private final JTextField tminQuantity;
    private final JTextField tpublishername;
    private final JTextField tisbn;
    private final JTextField tbookauthor;
    private final JComboBox<String> year;
    private final JTextArea tadd;
    private final JButton sub;
    private final JButton addAuthor;
    private final JLabel res;
    private final List<String> authors;
    IBook oldBook;
    boolean edit ;
    int labelWidth = 150;
    int labelHeight = 20;
    int labelXPosition = 50;
    int textXPosition = 200;

    // constructor, to initialize the components
    // with default values.
    public BookCreation(boolean edit, int id)
    {
        this.edit = edit;
        tisbn = new JTextField();
        tpublisherphone = new JTextField();
        ttitle = new JTextField();
        tpublishername = new JTextField();
        tadd = new JTextArea();
        tpublicationyear = new JTextField();
        tprice = new JTextField();
        authors = new ArrayList<>();
        String[] categories = {
                "Science", "Art", "Religion", "History", "Geography"
        };
        year = new JComboBox<>(categories);
        tminQuantity = new JTextField();
        tcurrQuantity = new JTextField();
        tbookauthor = new JTextField();

        if(edit){
            oldBook = bookService.findBookDetails(id);
            tisbn.setText(Integer.toString(id));
            tpublisherphone.setText(oldBook.getPublisher().getTelephoneNumber());
            ttitle.setText(oldBook.getTitle());
            tpublishername.setText(oldBook.getPublisher().getName());
            tadd.setText(oldBook.getPublisher().getAddress());
            tpublicationyear.setText(oldBook.getPublicationYear());
            tprice.setText(Float.toString(oldBook.getSellingPrice()));
            year.setSelectedItem(oldBook.getCategory());
            tminQuantity.setText(Integer.toString(oldBook.getMinQuantity()));
            tcurrQuantity.setText(Integer.toString(oldBook.getCurrentQuantity()));
        }
        setTitle("Book Store");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);
        int position = 100;
        JLabel title = new JLabel(edit? "Edit Book":"Create new Book");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(labelWidth + 150, 30);
        title.setLocation(labelXPosition, 30);
        c.add(title);

        title = new JLabel("title");
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        title.setSize(labelWidth, labelHeight);
        title.setLocation(labelXPosition, position);
        c.add(title);

        ttitle.setFont(new Font("Arial", Font.PLAIN, 15));
        ttitle.setSize(190, labelHeight);
        ttitle.setLocation(textXPosition, position);
        c.add(ttitle);

        JLabel publicationYear = new JLabel("Publication Year");
        publicationYear.setFont(new Font("Arial", Font.PLAIN, 15));
        publicationYear.setSize(labelWidth, labelHeight);
        publicationYear.setLocation(labelXPosition + 400, position);
        c.add(publicationYear);

        tpublicationyear.setFont(new Font("Arial", Font.PLAIN, 15));
        tpublicationyear.setSize(190, labelHeight);
        tpublicationyear.setLocation(labelXPosition + 550, position);
        c.add(tpublicationyear);


        position = position + 50;
        JLabel isbn = new JLabel("Book ISBN ");
        isbn.setFont(new Font("Arial", Font.PLAIN, 15));
        isbn.setSize(labelWidth, labelHeight);
        isbn.setLocation(labelXPosition, position);
        c.add(isbn);

        tisbn.setFont(new Font("Arial", Font.PLAIN, 15));
        tisbn.setSize(190, labelHeight);
        tisbn.setLocation(textXPosition, position);
        c.add(tisbn);

        JLabel price = new JLabel("Selling Price ");
        price.setFont(new Font("Arial", Font.PLAIN, 15));
        price.setSize(labelWidth, labelHeight);
        price.setLocation(labelXPosition + 400, position);
        c.add(price);

        tprice.setFont(new Font("Arial", Font.PLAIN, 15));
        tprice.setSize(190, labelHeight);
        tprice.setLocation(labelXPosition + 550, position);
        c.add(tprice);

        position = position + 50;
        JLabel publisherName = new JLabel("Publisher Name ");
        publisherName.setFont(new Font("Arial", Font.PLAIN, 15));
        publisherName.setSize(labelWidth, labelHeight);
        publisherName.setLocation(labelXPosition, position);
        c.add(publisherName);

        tpublishername.setFont(new Font("Arial", Font.PLAIN, 15));
        tpublishername.setSize(190, labelHeight);
        tpublishername.setLocation(textXPosition, position);
        c.add(tpublishername);

        JLabel bookAuthors = new JLabel("Book Authors ");
        bookAuthors.setFont(new Font("Arial", Font.PLAIN, 15));
        bookAuthors.setSize(labelWidth, labelHeight);
        bookAuthors.setLocation(labelXPosition + 400, position);
        c.add(bookAuthors);

        tbookauthor.setFont(new Font("Arial", Font.PLAIN, 15));
        tbookauthor.setSize(190, labelHeight);
        tbookauthor.setLocation(labelXPosition + 550, position);
        c.add(tbookauthor);

        addAuthor = new JButton("+");
        addAuthor.setFont(new Font("Arial", Font.PLAIN, 15));
        addAuthor.setSize(50, 20);
        addAuthor.setLocation(labelXPosition + 760, position);
        addAuthor.addActionListener(this);
        c.add(addAuthor);

        position = position + 50;
        JLabel publisherPhoneNumber = new JLabel("Publisher Phone ");
        publisherPhoneNumber.setFont(new Font("Arial", Font.PLAIN, 15));
        publisherPhoneNumber.setSize(labelWidth, labelHeight);
        publisherPhoneNumber.setLocation(labelXPosition, position);
        c.add(publisherPhoneNumber);

        tpublisherphone.setFont(new Font("Arial", Font.PLAIN, 15));
        tpublisherphone.setSize(190, labelHeight);
        tpublisherphone.setLocation(textXPosition, position);
        c.add(tpublisherphone);

        JLabel currQuantity = new JLabel("Current Quantity ");
        currQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        currQuantity.setSize(labelWidth, labelHeight);
        currQuantity.setLocation(labelXPosition + 400, position);
        c.add(currQuantity);

        tcurrQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        tcurrQuantity.setSize(190, labelHeight);
        tcurrQuantity.setLocation(labelXPosition + 550, position);
        c.add(tcurrQuantity);

        position = position + 50;

        JLabel category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.PLAIN, 15));
        category.setSize(labelWidth, labelHeight);
        category.setLocation(labelXPosition, position);
        c.add(category);

        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(190, labelHeight);
        year.setLocation(textXPosition, position);
        c.add(year);

        JLabel minQuantity = new JLabel("Minimum Quantity ");
        minQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        minQuantity.setSize(labelWidth, labelHeight);
        minQuantity.setLocation(labelXPosition + 400, position);
        c.add(minQuantity);

        tminQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        tminQuantity.setSize(190, labelHeight);
        tminQuantity.setLocation(labelXPosition + 550, position);
        c.add(tminQuantity);

        position = position + 50;

        JLabel add = new JLabel("Publisher Address");
        add.setFont(new Font("Arial", Font.PLAIN, 15));
        add.setSize(labelWidth, labelHeight);
        add.setLocation(labelXPosition, position);
        c.add(add);

        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(190, labelHeight);
        tadd.setLocation(textXPosition, position);
        tadd.setLineWrap(true);
        c.add(tadd);


        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 15);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sub ) {
            BookBuilder bookBuilder = constructBook();
            if(!edit) {
                if (bookBuilder.generateBookTuple() != null)
                    res.setText("Creation Successfully..");
                else
                    res.setText("Creation Failed !");
            }else
                if(bookService.editBook(oldBook, bookBuilder.generateBook()))
                    res.setText("Successfully Edited");
                else
                    res.setText("Edition Failed !");
        }else if(e.getSource() == addAuthor){
            authors.add(tbookauthor.getText());
            tbookauthor.setText("");
        }
    }
    private BookBuilder constructBook(){
        BookBuilder bookBuilder = new BookBuilder();
        bookBuilder.setBookId(Integer.parseInt(tisbn.getText()));
        bookBuilder.setPublisherTelephoneNum(tpublisherphone.getText());
        bookBuilder.setTitle(ttitle.getText());
        bookBuilder.setPublisherName(tpublishername.getText());
        bookBuilder.setPublisherAddress(tadd.getText());
        bookBuilder.setPublicationYear(tpublicationyear.getText());
        bookBuilder.setSellingPrice(Float.parseFloat(tprice.getText()));
        bookBuilder.setCategory(Objects.requireNonNull(year.getSelectedItem()).toString());
        bookBuilder.setMinQuantity(Integer.parseInt(tminQuantity.getText()));
        bookBuilder.setCurrentQuantity(Integer.parseInt(tcurrQuantity.getText()));
        bookBuilder.setBookAuthors(authors.toArray(new String[0]));
        return bookBuilder;
    }

    public static void main(String[] args) {
        new BookCreation(false, 123);
    }

}
