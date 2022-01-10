package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import java.util.List;

public class Order extends JFrame implements ActionListener {
    // Components of the Form
    private Container container;
    private JButton back;
    private Container itemsContainer;
    private JScrollPane itemsScrollPane;
    private Container costContainer;
    private Container creditContainer;
    private JLabel creditNumber;
    private JTextField creditNumberField;
    private JLabel creditExpir;
    private JFormattedTextField creditExpirField;
    private JButton validateCard;
    private JLabel validation;
    private JButton placeOrder;

    private List<Item> items;

    public Order(List<Item> items) {
        this.items = items;

        /* Create Main Frame */
        setTitle("Book Store");
        setBounds(300, 100, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        Icon backIcon = new ImageIcon("src/GUI/Icons/left-arrow.png");
        back = new JButton(backIcon);
        back.setText("Back");
        back.setIconTextGap(10);
        back.setFont(new Font("Arial", Font.PLAIN, 16));
        back.setSize(110, 35);
        back.setLocation(15, 15);
        back.addActionListener(this);
        container.add(back);

        /* items container */
        itemsContainer = new JPanel();
        itemsContainer.setSize(400,50*items.size());
        // configure the container as a box layout
        BoxLayout itemsBoxLayout = new BoxLayout(itemsContainer, BoxLayout.Y_AXIS);
        itemsContainer.setLayout(itemsBoxLayout);
        for(Item item : this.items) {
            itemsContainer.add(createItem(item));
        }
        itemsScrollPane = new JScrollPane(itemsContainer);
        itemsScrollPane.setSize(400,420);
        itemsScrollPane.setLocation(50,70);
        container.add(itemsScrollPane);

        /* cost container */
        costContainer = new JPanel();
        costContainer.setSize(250,60);
        costContainer.setLocation(480,70);
        // configure the container as a box layout
        BoxLayout costLayout = new BoxLayout(costContainer, BoxLayout.Y_AXIS);
        costContainer.setLayout(costLayout);

        JLabel totalLabel = new JLabel("Total cost = ");
        totalLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        totalLabel.setSize(250,30);
        costContainer.add(totalLabel);
        container.add(costContainer);

        Container cashAmountContainer = new JPanel();
        cashAmountContainer.setSize(250,30);
        // configure the container as a flow layout
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT, 0, 0);
        cashAmountContainer.setLayout(flowLayout);
        JLabel costLabel = new JLabel(" $"); //
        costLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        cashAmountContainer.add(costLabel);
        costContainer.add(cashAmountContainer);
        container.add(costContainer);

        /* credit card container */
        creditContainer = new JPanel();
        creditContainer.setSize(280,200);
        creditContainer.setLocation(470,150);
        //
        JLabel title = new JLabel("Credit card Info.");
        title.setFont(new Font("Georgia", Font.ITALIC, 20));
        title.setSize(300, 35);
        creditContainer.add(title);

        Container creditInfoContainer = new JPanel();
        creditInfoContainer.setSize(270,200);
        creditInfoContainer.setLocation(480,130);
        // configure the container as a grid layout
        GridLayout creditInfoLayout = new GridLayout(2,2,15,15);
        creditInfoContainer.setLayout(creditInfoLayout);

        creditNumber = new JLabel("Card Number");
        creditNumber.setFont(new Font("Georgia", Font.PLAIN, 18));
        creditNumber.setSize(50, 28);
        creditInfoContainer.add(creditNumber);

        creditNumberField = new JTextField();
        creditNumberField .setFont(new Font("Arial", Font.PLAIN, 16));
        creditNumberField .setSize(280, 30);
        creditInfoContainer.add(creditNumberField);

        creditExpir= new JLabel("Card Exp. Date");
        creditExpir.setFont(new Font("Georgia", Font.PLAIN, 18));
        creditExpir.setSize(50, 28);
        creditInfoContainer.add(creditExpir);

        creditExpirField = new JFormattedTextField();
        creditExpirField.setFont(new Font("Arial", Font.PLAIN, 16));
        creditExpirField.setSize(280, 30);
        creditInfoContainer.add(creditExpirField);
        //
        creditContainer.add(creditInfoContainer);

        validateCard = new JButton("Validate Card");
        validateCard.setFont(new Font("Arial", Font.PLAIN, 16));
        validateCard.setSize(220, 28);
        creditContainer.add(validateCard);

        container.add(creditContainer);

        validation = new JLabel();
        validation.setFont(new Font("Georgia", Font.PLAIN, 18));
        validation.setSize(50, 28);
        validation.setLocation(500,350);
        container.add(validation);

        /* place order button */
        placeOrder = new JButton("Place Order");
        placeOrder.setBackground(Color.green);
        placeOrder.setFont(new Font("Georgia", Font.PLAIN, 18));
        placeOrder.setSize(150, 40);
        placeOrder.setLocation(600, 500);
        placeOrder.addActionListener(this);
        container.add(placeOrder);

        setVisible(true);

    }

    public Container createItem(Item item) {
        Container itemContainer = new JPanel();
        itemContainer.setSize(500,50);
        // configure the container as a flow layout
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING, 30, 5);
        itemContainer.setLayout(flowLayout);

        JLabel itemLabel = new JLabel(item.getLabel());
        itemLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        itemLabel.setSize(250, 50);
        itemContainer.add(itemLabel);

        Container controls = new JPanel();
        // configure the container as a flow layout
        FlowLayout flowLayoutIn = new FlowLayout(FlowLayout.CENTER, 25, 0);
        controls.setLayout(flowLayoutIn);

        JLabel amount = new JLabel(String.valueOf(item.getAmount()));
        amount.setFont(new Font("Arial", Font.PLAIN, 20));
        amount.setSize(50, 20);
        controls.add(amount);

        JLabel cost = new JLabel(item.getCost() + " $");
        cost.setFont(new Font("Arial", Font.PLAIN, 20));
        cost.setSize(50, 20);
        controls.add(cost);

        itemContainer.add(controls);

        return itemContainer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("book1", 10, 100));
        items.add(new Item("book2", 10, 100));
        items.add(new Item("book3", 10, 100));
        items.add(new Item("book4", 10, 100));
        items.add(new Item("book5", 10, 100));
        items.add(new Item("book6", 10, 100));
        items.add(new Item("book7", 10, 100));
        items.add(new Item("book8", 10, 100));
        items.add(new Item("book9", 10, 100));
        items.add(new Item("book10", 10, 100));
        items.add(new Item("book11", 10, 100));
        items.add(new Item("book12", 10, 100));
        items.add(new Item("book13", 10, 100));
        items.add(new Item("book14", 10, 100));
        items.add(new Item("book15", 10, 100));

        Order obj = new Order(items);
    }
}
