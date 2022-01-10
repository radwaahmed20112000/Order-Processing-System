package GUI;

import services.CustomerService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SignIn extends JFrame implements ActionListener {

    // Components of the Form
    private Container container;
    private JLabel title;
    private JLabel mail;
    private JTextField mailField;
    private JLabel password;
    private JPasswordField passwordField;
    private JLabel userType;
    private JRadioButton customer;
    private JRadioButton manager;
    private ButtonGroup btnGroup;
    private JButton signIn;
    private JButton reset;
    private JLabel signUpLabel;
    private JButton signUp;
    private JLabel result;

    public SignIn() {
        /* Create Main Frame */
        setTitle("Welcome to our Book Store");
        setBounds(400, 100, 550, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Book Store");
        title.setFont(new Font("Georgia", Font.ITALIC, 30));
        title.setSize(300, 35);
        title.setLocation(210, 30);
        container.add(title);

        mail = new JLabel("Mail Address");
        mail.setFont(new Font("Georgia", Font.PLAIN, 18));
        mail.setSize(120, 25);
        mail.setLocation(100, 120);
        container.add(mail);

        mailField = new JTextField();
        mailField.setFont(new Font("Arial", Font.PLAIN, 16));
        mailField.setSize(220, 28);
        mailField.setLocation(220, 120);
        container.add(mailField);

        password = new JLabel("Password");
        password.setFont(new Font("Georgia", Font.PLAIN, 18));
        password.setSize(120, 28);
        password.setLocation(100, 180);
        container.add(password);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setSize(220, 28);
        passwordField.setLocation(220, 180);
        container.add(passwordField);

        userType = new JLabel("Type");
        userType.setFont(new Font("Georgia", Font.PLAIN, 18));
        userType.setSize(100, 28);
        userType.setLocation(100, 230);
        container.add(userType);

        customer = new JRadioButton("Customer");
        customer.setFont(new Font("Arial", Font.PLAIN, 16));
        customer.setSelected(true);
        customer.setSize(100, 28);
        customer.setLocation(220, 230);
        container.add(customer);

        manager = new JRadioButton("Manager");
        manager.setFont(new Font("Arial", Font.PLAIN, 16));
        manager.setSelected(false);
        manager.setSize(100, 28);
        manager.setLocation(330, 230);
        container.add(manager);

        btnGroup = new ButtonGroup();
        btnGroup.add(customer);
        btnGroup.add(manager);

        signIn = new JButton("Sign In");
        signIn.setFont(new Font("Arial", Font.PLAIN, 15));
        signIn.setSize(100, 25);
        signIn.setLocation(160, 300);
        signIn.addActionListener(this);
        container.add(signIn);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 25);
        reset.setLocation(300, 300);
        reset.addActionListener(this);
        container.add(reset);

        signUpLabel = new JLabel("Didn't sign up?");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpLabel.setSize(120, 28);
        signUpLabel.setLocation(170, 350);
        container.add(signUpLabel);

        signUp = new JButton("Sign Up");
        signUp.setFont(new Font("Arial", Font.PLAIN, 15));
        signUp.setSize(100, 25);
        signUp.setLocation(280, 350);
        signUp.addActionListener(this);
        container.add(signUp);

        result = new JLabel();
        result.setFont(new Font("Arial", Font.PLAIN, 14));
        result.setSize(120, 28);
        result.setLocation(170, 400);
        container.add(result);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CustomerService obj = new CustomerService();
        if (e.getSource() == signIn) {
            String mail = mailField.getText();
            char[] pass = passwordField.getPassword();
            String password = String.valueOf(pass);
            boolean type = customer.isSelected();
            if(obj.signIn(mail,password,type)) {
                setVisible(false);
                Home homeObj = new Home();
                result.setText("Sign In Successfully..");
            } else {
                result.setText("Sign In failed..");
            }
        } else if (e.getSource() == reset) {
            String def = "";
            mailField.setText(def);
            passwordField.setText(def);
        } else if(e.getSource() == signUp) {
            setVisible(false);
            SignUp signObj = new SignUp();
        }
    }

    public static void main(String[] args) {
        SignIn obj = new SignIn();
    }
}

