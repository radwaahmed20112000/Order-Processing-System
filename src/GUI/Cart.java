package GUI;

import interfaces.ICart;
import models.CartManager;
import services.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends JFrame implements ActionListener {
    // Components of the Form
    private Container container;
    private JButton back;
    private Container itemsContainer;
    private JScrollPane scrollPane;
    private mybutton checkout;
    JTextField textField_1;
    ICart cart ;
    private List<String> items;
    private List<Container> containers = new ArrayList<>();

    public Cart(ICart cart ) {
        this.cart = cart;
        this.items = cart.viewCartDetails();

        /* Create Main Frame */
        setTitle("Book Store");
        setBounds(300, 100, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(253, 232, 215));


        Icon backIcon = new ImageIcon("src/GUI/Icons/left-arrow.png");
        back = new JButton(backIcon);
        back.setText("Back");
        back.setIconTextGap(10);
        back.setFont(new Font("Arial", Font.PLAIN, 16));
        back.setBackground(new Color(153, 102, 128));
        back.setSize(110, 35);
        back.setLocation(15, 15);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
               UserProfile user = new UserProfile(CustomerService.currentUser);

            }
        });
        container.add(back);

        itemsContainer = new JPanel();
        itemsContainer.setSize(900,30*items.size());

        // configure the container as a box layout
        BoxLayout boxLayout = new BoxLayout(itemsContainer, BoxLayout.Y_AXIS);
        itemsContainer.setLayout(boxLayout);
        for(String item : this.items) {
            Container newItem = createItem(item);
            containers.add(newItem);
            itemsContainer.add(newItem);
        }
        scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setSize(800,420);
        scrollPane.setLocation(50,70);
        container.add(scrollPane);

        checkout = new mybutton("Checkout");
        checkout.setSize(120, 40);
        checkout.setLocation(600, 500);
        checkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cart.buyCart();
                }
        });
        container.add(checkout);


        JLabel itemLabel4 = new JLabel(String.valueOf("Total price : "+ cart.getCartPrice()));
        itemLabel4.setFont(new Font("Georgia", Font.PLAIN, 20));
        itemLabel4.setSize(250, 50);
        itemLabel4.setLocation(400, 500);
        container.add(itemLabel4);

        setVisible(true);
    }

    public Container createItem(String item) {
        String[] itemDetails = item.split(":");

        Container itemContainer = new JPanel();
        itemContainer.setSize(800,50);
        // configure the container as a flow layout
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 20, 3);
        itemContainer.setLayout(flowLayout);

        String itemShowTitle = "Book Title: " + itemDetails[1];
        JLabel itemLabel1 = new JLabel(itemShowTitle );
        itemLabel1.setFont(new Font("Georgia", Font.PLAIN, 20));
        itemLabel1.setSize(250, 50);
        itemContainer.add(itemLabel1);

        String itemShowPrice = "Book Price: "+itemDetails[2];
        JLabel itemLabel2 = new JLabel(itemShowPrice );
        itemLabel2.setFont(new Font("Georgia", Font.PLAIN, 20));
        itemLabel2.setSize(250, 50);
        itemContainer.add(itemLabel2);

        String itemShowCount = "Count: "+itemDetails[3];
        JLabel itemLabel3 = new JLabel(itemShowPrice );
        itemLabel3.setFont(new Font("Georgia", Font.PLAIN, 20));
        itemLabel3.setSize(250, 50);
        itemContainer.add(itemLabel3);

        Container controls = new JPanel();


        this.textField_1 = new JTextField();
        this.textField_1.setSize( 20, 20);
        this.textField_1.setText(itemDetails[3]);
        controls.add(this.textField_1);
        this.textField_1.setColumns(10);



        JButton btnUpdate = new JButton("Go");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int count = 0;
                if(!textField_1.getText().equals("")){
                      count = Integer.parseInt(textField_1.getText());
                      System.out.println(count);
                      cart.editCartBookCount(Integer.parseInt(itemDetails[0]),count);
                    dispose();
                    Cart c = new Cart(cart);
            }}
        });
        controls.add(btnUpdate);

        JButton btnremove = new JButton("remove");
        btnremove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cart.removeFromCart(Integer.parseInt(itemDetails[0]));
                dispose();
                Cart c = new Cart(cart);
            }
        });
        controls.add(btnremove);

        itemContainer.add(controls);
        return itemContainer;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
