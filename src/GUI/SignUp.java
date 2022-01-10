package GUI;

import interfaces.IUser;
import services.CustomerService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SignUp extends JFrame implements ActionListener {

    // Components of the Form
    private Container container;
    private JLabel title;
    private JLabel userName;
    private JTextField userNameField;
    private JLabel firstName;
    private JTextField firstNameField;
    private JLabel lastName;
    private JTextField lastNameField;
    private JLabel userType;
    private JRadioButton customer;
    private JRadioButton manager;
    private ButtonGroup btnGroup;
    private JLabel mail;
    private JTextField mailField;
    private JLabel password;
    private JPasswordField passwordField;
    private JLabel phoneNumber;
    private JTextField phoneNumberField;
    private JLabel address;
    private JTextArea addressField;
    private JButton register;
    private JButton reset;
    private JLabel result;

    public SignUp() {
        /* Create Main Frame */
        setTitle("Welcome to our Book Store");
        setBounds(300, 100, 700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Georgia", Font.ITALIC, 30));
        title.setSize(300, 35);
        title.setLocation(250, 30);
        container.add(title);

        userName = new JLabel("User Name");
        userName.setFont(new Font("Georgia", Font.PLAIN, 18));
        userName.setSize(100, 28);
        userName.setLocation(70, 100);
        container.add(userName);

        userNameField = new JTextField();
        userNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        userNameField.setSize(200, 28);
        userNameField.setLocation(190, 100);
        container.add(userNameField);

        firstName = new JLabel("First Name");
        firstName.setFont(new Font("Georgia", Font.PLAIN, 18));
        firstName.setSize(100, 28);
        firstName.setLocation(70, 140);
        container.add(firstName);

        firstNameField = new JTextField();
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        firstNameField.setSize(160, 28);
        firstNameField.setLocation(190, 140);
        container.add(firstNameField);

        lastName = new JLabel("Last Name");
        lastName.setFont(new Font("Georgia", Font.PLAIN, 18));
        lastName.setSize(100, 25);
        lastName.setLocation(370, 140);
        container.add(lastName);

        lastNameField = new JTextField();
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        lastNameField.setSize(160, 28);
        lastNameField.setLocation(480, 140);
        container.add(lastNameField);

        userType = new JLabel("Type");
        userType.setFont(new Font("Georgia", Font.PLAIN, 18));
        userType.setSize(100, 28);
        userType.setLocation(70, 180);
        container.add(userType);

        customer = new JRadioButton("Customer");
        customer.setFont(new Font("Arial", Font.PLAIN, 16));
        customer.setSelected(true);
        customer.setSize(100, 28);
        customer.setLocation(190, 180);
        container.add(customer);

        manager = new JRadioButton("Manager");
        manager.setFont(new Font("Arial", Font.PLAIN, 16));
        manager.setSelected(false);
        manager.setSize(100, 28);
        manager.setLocation(300, 180);
        container.add(manager);

        btnGroup = new ButtonGroup();
        btnGroup.add(customer);
        btnGroup.add(manager);

        mail = new JLabel("Mail Address");
        mail.setFont(new Font("Georgia", Font.PLAIN, 18));
        mail.setSize(120, 28);
        mail.setLocation(70, 220);
        container.add(mail);

        mailField = new JTextField();
        mailField.setFont(new Font("Arial", Font.PLAIN, 16));
        mailField.setSize(200, 28);
        mailField.setLocation(190, 220);
        container.add(mailField);

        password = new JLabel("Password");
        password.setFont(new Font("Georgia", Font.PLAIN, 18));
        password.setSize(120, 28);
        password.setLocation(70, 260);
        container.add(password);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setSize(200, 28);
        passwordField.setLocation(190, 260);
        container.add(passwordField);

        phoneNumber = new JLabel("Phone No.");
        phoneNumber.setFont(new Font("Georgia", Font.PLAIN, 18));
        phoneNumber.setSize(120, 25);
        phoneNumber.setLocation(70, 300);
        container.add(phoneNumber);

        phoneNumberField = new JTextField();
        phoneNumberField.setFont(new Font("Arial", Font.PLAIN, 16));
        phoneNumberField.setSize(200, 28);
        phoneNumberField.setLocation(190, 300);
        container.add(phoneNumberField);

        address = new JLabel("Address");
        address.setFont(new Font("Georgia", Font.PLAIN, 18));
        address.setSize(100, 20);
        address.setLocation(70, 340);
        container.add(address);

        addressField = new JTextArea();
        addressField.setFont(new Font("Arial", Font.PLAIN, 15));
        addressField.setSize(300, 90);
        addressField.setLocation(190, 340);
        addressField.setLineWrap(true);
        container.add(addressField);

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(100, 25);
        register.setLocation(220, 460);
        register.addActionListener(this);
        container.add(register);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 25);
        reset.setLocation(350, 460);
        reset.addActionListener(this);
        container.add(reset);

        result = new JLabel();
        result.setFont(new Font("Arial", Font.PLAIN, 14));
        result.setSize(120, 28);
        result.setLocation(170, 500);
        container.add(result);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CustomerService obj = new CustomerService();
        if (e.getSource() == register) {
            String userName = userNameField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String shippingAddress = addressField.getText();
            String mail = mailField.getText();
            char[] pass = passwordField.getPassword();
            String password = String.valueOf(pass);
            boolean type = customer.isSelected();
            if(obj.signUp(firstName,lastName,password,mail,phoneNumber,shippingAddress,type,userName)) {
                result.setText("Register Successfully..");
                dispose();
                IUser user = new IUser (firstName,lastName ,password,mail,phoneNumber,shippingAddress,userName);
                UserProfile userProfile = new UserProfile(user);
            } else {
                result.setText("Register failed..");
            }
        } else if (e.getSource() == reset) {
            String def = "";
            mailField.setText(def);
            passwordField.setText(def);
        }
    }

}
