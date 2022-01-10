package GUI;

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
    private JButton signIn;
    private JButton reset;
    private JLabel signUpLabel;

    public SignIn() {
        /* Create Main Frame */
        setTitle("Welcome to our Book Store");
        setBounds(400, 100, 550, 400);
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

        signIn = new JButton("Sign In");
        signIn.setFont(new Font("Arial", Font.PLAIN, 15));
        signIn.setSize(100, 25);
        signIn.setLocation(160, 250);
        signIn.addActionListener(this);
        container.add(signIn);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 25);
        reset.setLocation(300, 250);
        reset.addActionListener(this);
        container.add(reset);

        signUpLabel = new JLabel("Didn't sign up?");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpLabel.setSize(120, 28);
        signUpLabel.setLocation(170, 300);
        container.add(signUpLabel);

        reset = new JButton("Sign Up");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 25);
        reset.setLocation(280, 300);
        reset.addActionListener(this);
        container.add(reset);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        SignIn obj = new SignIn();
    }
}

