package GUI;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import interfaces.IUser;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserProfile extends JFrame {
    public static final int labelDimWidth = 200;
    public static final int textDimWidth = 200;
    public static final int SmallButtonWidth = 80;
    private JPanel contentPane;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;

    public UserProfile(final IUser user) {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 800, 500);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setBackground(new Color(253, 232, 215));
        this.contentPane.setLayout((LayoutManager)null);
        JLabel lblName = new JLabel("First Name");
        lblName.setBounds(57, 62, 200, 14);
        this.contentPane.add(lblName);
        this.textField_1 = new JTextField();
        this.textField_1.setBounds(206, 59, 200, 20);
        this.textField_1.setText(user.getFirstName());
        this.contentPane.add(this.textField_1);
        this.textField_1.setColumns(10);
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(57, 93, 200, 14);
        this.contentPane.add(lblLastName);
        this.textField_2 = new JTextField();
        this.textField_2.setBounds(206, 90, 200, 20);
        this.textField_2.setText(user.getLastName());
        this.contentPane.add(this.textField_2);
        this.textField_2.setColumns(10);
        JLabel lblEmail = new JLabel("Email Address");
        lblEmail.setBounds(57, 124, 200, 14);
        this.contentPane.add(lblEmail);
        this.textField_3 = new JTextField();
        this.textField_3.setBounds(206, 121, 200, 20);
        this.textField_3.setText(user.getEmailAddress());
        this.contentPane.add(this.textField_3);
        this.textField_3.setColumns(10);
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(57, 155, 200, 14);
        this.contentPane.add(lblPassword);
        this.textField_4 = new JTextField();
        this.textField_4.setBounds(206, 152, 200, 20);
        this.textField_4.setText(user.getPassword());
        this.contentPane.add(this.textField_4);
        this.textField_4.setColumns(10);
        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setBounds(57, 184, 200, 14);
        this.contentPane.add(lblPhone);
        this.textField_5 = new JTextField();
        this.textField_5.setBounds(206, 181, 200, 20);
        this.textField_5.setText(user.getPhoneNumber());
        this.contentPane.add(this.textField_5);
        this.textField_5.setColumns(10);
        JLabel lblShippingAddress = new JLabel("Shipping Address");
        lblShippingAddress.setBounds(57, 215, 200, 14);
        this.contentPane.add(lblShippingAddress);
        this.textField_6 = new JTextField();
        this.textField_6.setBounds(206, 212, 200, 20);
        this.textField_6.setText(user.getShippingAddress());
        this.contentPane.add(this.textField_6);
        this.textField_6.setColumns(10);
        JLabel lblRegistrationForm = new JLabel("User Profile");
        lblRegistrationForm.setBounds(168, 11, 200, 37);
        this.contentPane.add(lblRegistrationForm);
        mybutton btnLogOut = new mybutton("Log Out");
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user.logOut();
                dispose();
            }
        });
        btnLogOut.setBounds(650, 11, 80, 23);
        this.contentPane.add(btnLogOut);

        mybutton btnCart = new mybutton("Cart");
        btnCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Cart c = new Cart(user.getCart());

            }
        });
        btnCart.setBounds(550, 11, 80, 23);
        this.contentPane.add(btnCart);

        mybutton btnRegister = new mybutton("Save!");
        btnRegister.setBounds(104, 275, 200, 23);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boolean aa = false;
                String email = UserProfile.this.textField_3.getText();
                String firstName = UserProfile.this.textField_1.getText();
                String lastName = UserProfile.this.textField_2.getText();
                String password = UserProfile.this.textField_4.getText();
                String phoneNumber = UserProfile.this.textField_5.getText();
                String shippingAddress = UserProfile.this.textField_6.getText();
                if (firstName.equals("")) {
                    JOptionPane.showMessageDialog((Component)null, "First Name Field can't be empty");
                } else if (lastName.equals("")) {
                    JOptionPane.showMessageDialog((Component)null, "Last Name Field can't be empty");
                } else if (email.equals("")) {
                    JOptionPane.showMessageDialog((Component)null, "Email Address Field can't be empty");
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog((Component)null, "Password Field can't be empty");
                } else if (phoneNumber.equals("")) {
                    JOptionPane.showMessageDialog((Component)null, "Phone Number Field can't be empty");
                } else if (shippingAddress.equals("")) {
                    JOptionPane.showMessageDialog((Component)null, "Shipping Address Field can't be empty");
                } else {
                    aa = true;
                }
//TODO username
                if (aa) {
                    IUser newData = new IUser(firstName, lastName, password, email, phoneNumber, shippingAddress,firstName+lastName);
                    int res = user.editProfile(newData);
                    if (res == 1) {
                        JOptionPane.showMessageDialog((Component)null, "Changes Saved");
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "Can't save wrong data");
                    }
                }

            }
        });
        this.contentPane.add(btnRegister);
        mybutton btnHome = new mybutton("Home");
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                Home h = new Home();
            }
        });
        btnHome.setBounds(350, 275, 200, 23);
        this.contentPane.add(btnHome);
        this.setVisible(true);
    }
}
