package GUI;

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
    private JButton checkout;

    private List<String> items;
    private List<Container> containers = new ArrayList<>();

    public Cart(List items) {
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
        back.setSize(110, 40);
        back.setLocation(15, 15);
        back.addActionListener(this);
        container.add(back);

        itemsContainer = new JPanel();
        itemsContainer.setSize(700,50*items.size());
        // configure the container as a box layout
        BoxLayout boxLayout = new BoxLayout(itemsContainer, BoxLayout.Y_AXIS);
        itemsContainer.setLayout(boxLayout);
        for(String item : this.items) {
            Container newItem = createItem(item);
            containers.add(newItem);
            itemsContainer.add(newItem);
        }
        scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setSize(700,420);
        scrollPane.setLocation(50,70);
        container.add(scrollPane);

        checkout = new JButton("Checkout");
        checkout.setBackground(Color.green);
        checkout.setFont(new Font("Georgia", Font.PLAIN, 18));
        checkout.setSize(120, 40);
        checkout.setLocation(600, 500);
        checkout.addActionListener(this);
        container.add(checkout);

        setVisible(true);
    }

    public Container createItem(String item) {
        Container itemContainer = new JPanel();
        itemContainer.setSize(500,50);
        // configure the container as a flow layout
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 80, 5);
        itemContainer.setLayout(flowLayout);

        JLabel itemLabel = new JLabel(item);
        itemLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        itemLabel.setSize(250, 50);
        itemContainer.add(itemLabel);

        Container controls = new JPanel();

        Icon incrementIcon = new ImageIcon("src/GUI/Icons/plus.png");
        JButton increment = new JButton(incrementIcon);
        increment.setSize(20, 20);
        increment.setMargin(new Insets(2,2,2,2));
        increment.addActionListener(this);
        controls.add(increment);

        JLabel amount = new JLabel("0");
        amount.setFont(new Font("Georgia", Font.PLAIN, 20));
        amount.setSize(50, 20);
        controls.add(amount);

        Icon decrementIcon = new ImageIcon("src/GUI/Icons/minus.png");
        JButton decrement = new JButton(decrementIcon);
        decrement.setSize(20, 20);
        decrement.setMargin(new Insets(2,2,2,2));
        decrement.addActionListener(this);
        controls.add(decrement);

        itemContainer.add(controls);

        return itemContainer;
    }

    public void addItem(String item) {
        this.items.add(item);
        Container addedItem = createItem(item);
        itemsContainer.add(addedItem);
    }

    public Component searchItem(String item) {
        for(int i=0; i<containers.size(); i++) {
            try {
                Component[] components = containers.get(i).getComponents();
                JLabel itemLabel = (JLabel) components[0];
                if(itemLabel.getText().equals(item)) {
                    return containers.get(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void removeItem(String item) {
        this.items.remove(item);
        Component removedItem = searchItem(item);
        itemsContainer.remove(removedItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("book1");
        items.add("book2");
        items.add("book3");
        items.add("book4");
        items.add("book5");
        items.add("book6");
        items.add("book7");
        items.add("book8");
        items.add("book9");
        items.add("book10");

        Cart obj = new Cart(items);
        //obj.removeItem("book6");
    }
}
