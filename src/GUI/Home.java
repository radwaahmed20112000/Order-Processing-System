package GUI;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import javax.swing.border.*;

public class Home extends JFrame {
    public Home(){
        initComponents();
    }

//    App myApp = new App();

//    private void composeActionPerformed(ActionEvent e) {
//        GUICompose compose =new GUICompose();
//    }
//
//    private void InboxActionPerformed(ActionEvent e) {
//        current = new Folder("inbox",GUISignIn.Email);
//        myApp.setViewingOptions(current,null,new Sort("Date ( Lastest )"));
//        EmailsList.myTable.buildGUI(1);
//
//    }
//
//    private void SentActionPerformed(ActionEvent e) {
//        current = new Folder("sent",GUISignIn.Email);
//        myApp.setViewingOptions(current,null,new Sort("Date ( Lastest )"));
//        EmailsList.myTable.buildGUI(1);
//
//
//    }
//
//    private void DraftsActionPerformed(ActionEvent e) {
//        current = new Folder("drafts",GUISignIn.Email);
//        myApp.setViewingOptions(current,null,new Sort("Date ( Lastest )"));
//        EmailsList.myTable.buildGUI(1);
//
//    }
//

//    private void contactsActionPerformed(ActionEvent e) {
//        GUIContactsDisplay guiContactsDisplay = new GUIContactsDisplay(GUISignIn.Email);
//    }


   /* private void createUIComponents() {
        thumb = new JLabel(new ImageIcon("81kTrSqMuLL._AC_SL1135_.jpg")) ;   }
*/

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hyl
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - hyl
        JPanel Menu = new JPanel();
        JButton Cart = new JButton();
        JPanel panel3 = new JPanel();
        ManageBooks = new JButton();
        ManageUsers = new JButton();
        ManageOrders = new JButton();
        Logout = new JButton();
        JPanel panel1 = new JPanel();
//        App myApp = new App();
//        myApp.setViewingOptions(MailGUI.current,null,new Sort("Date ( Lastest )"));

        //======== this ========
        setForeground(Color.white);
        setBackground(new Color(34, 40, 49));
        var contentPane = getContentPane();

        //======== Menu ========
        {
            Menu.setBorder(new LineBorder(Color.white));
            Menu.setBackground(new Color(34, 40, 49));
            Menu.setForeground(Color.white);

            //---- Cart ----
            Cart.setText("Cart");
            Cart.setBorder(new EmptyBorder(5, 5, 5, 5));
//            Cart.addActionListener(this::composeActionPerformed);

            //======== panel3 ========
            {
                panel3.setOpaque(false);
                panel3.setBackground(Color.white);
                panel3.setForeground(Color.white);

                //---- ManageBooks ----
                ManageBooks.setText("Manage books");
                ManageBooks.setOpaque(false);
                ManageBooks.setContentAreaFilled(false);
                ManageBooks.setBorder(new LineBorder(Color.white, 1, true));
                ManageBooks.setForeground(Color.white);
//                ManageBooks.addActionListener(e -> {
//                    InboxActionPerformed(e);
//                    InboxActionPerformed(e);
//                });

                //---- ManageUsers ----
                ManageUsers.setText("Manage users");
                ManageUsers.setOpaque(false);
                ManageUsers.setContentAreaFilled(false);
                ManageUsers.setBorder(new LineBorder(Color.white, 1, true));
                ManageUsers.setForeground(Color.white);
//                ManageUsers.addActionListener(this::SentActionPerformed);

                //---- ManageOrders ----
                ManageOrders.setText("Manage orders");
                ManageOrders.setOpaque(false);
                ManageOrders.setContentAreaFilled(false);
                ManageOrders.setBorder(new LineBorder(Color.white, 1, true));
                ManageOrders.setForeground(Color.white);
//                ManageOrders.addActionListener(this::DraftsActionPerformed);

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panel3Layout.createParallelGroup()
                                                .addComponent(ManageUsers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ManageBooks, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ManageOrders, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())
                );
                panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                        .addComponent(ManageBooks)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ManageUsers)
                                        .addComponent(ManageOrders)
                                        .addContainerGap())
                );
            }

            //---- Logout ----
            Logout.setText("Logout");
            Logout.setOpaque(false);
            Logout.setContentAreaFilled(false);
            Logout.setBorder(new LineBorder(Color.white, 1, true));
            Logout.setForeground(Color.white);
//            Logout.addActionListener(this::contactsActionPerformed);

            GroupLayout MenuLayout = new GroupLayout(Menu);
            Menu.setLayout(MenuLayout);
            MenuLayout.setHorizontalGroup(
                    MenuLayout.createParallelGroup()
                            .addComponent(panel3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(MenuLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(MenuLayout.createParallelGroup()
                                            .addComponent(Logout, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Cart, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            MenuLayout.setVerticalGroup(
                    MenuLayout.createParallelGroup()
                            .addGroup(MenuLayout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(Cart)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(Logout)
                                    .addContainerGap(264, Short.MAX_VALUE))
            );
        }

        //======== panel1 ========

        CardLayout cl = new CardLayout();
        BooksList el = new BooksList();
        panel1.setLayout(cl);
        panel1.add(el,"1");



        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(Menu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                        .addComponent(Menu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        cl.show(panel1,"1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    private void MailListTo(){
        ManageUsers.setForeground(Color.black);
        ManageBooks.setForeground(Color.black);
        ManageOrders.setForeground(Color.black);

    }
    private JButton ManageOrders;

    private JButton ManageBooks;
    private JButton ManageUsers;
    private JButton Logout;

//    public static Folder current = new Folder("inbox",GUISignIn.Email);
    // JFormDesigner - End of variables declaration  //GEN-END:variables
public static void main(String[] args) {
    Home m = new Home();
}
}
