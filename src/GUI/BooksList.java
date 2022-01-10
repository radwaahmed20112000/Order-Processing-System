/*
 * Created by JFormDesigner on Fri May 01 08:40:18 EET 2020
 */

package GUI;

import interfaces.IBook;
import services.BookService;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author hyl
 */

public class BooksList extends JPanel {
//    App myApp = new App();
    public BooksList() {
        initComponents();
    }

    private void filterActionPerformed(ActionEvent e) {
        FilterMenu filterMenu = new FilterMenu();
        filterMenu.setVisible(true);
        // TODO add your code here
    }

    private void ButtonSearchActionPerformed(ActionEvent e){
        BookService service = new BookService();
        if(textSearch.getText().trim().equals(""))
            JOptionPane.showMessageDialog(invisible,"Enter a word in the search bar");
        List<IBook> bookList = service.findBook(textSearch.getText().trim());

//        search = new Search(MailGUI.current,textSearch.getText());
        myBooksTable.buildGUI(bookList);
//        if(myTable.getNumberOfMails()==0){
//            JOptionPane.showMessageDialog(invisible, "No mail in this Folder");
//        }

    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hyl
        emailListHeader = new JPanel();
        searchFilter = new JPanel();
        filter = new JButton();
        textSearch = new JTextField();
        buttonSearch = new JButton();
        myBooksTable = new BooksTable(null);
        scrollMails = new JScrollPane(myBooksTable);

        //======== this ========
        setPreferredSize(new Dimension(754, 533));
        setMinimumSize(new Dimension(754, 533));

        //======== searchFilter ========
        {

            //---- filter ----
            filter.setText("Filter \ud83d\udd3d");
            filter.addActionListener(e -> filterActionPerformed(e));
//---- buttonSearch ----

            buttonSearch.setText("Search");
            buttonSearch.addActionListener(e -> ButtonSearchActionPerformed(e));
            //---- textSearch ----
            textSearch.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            textSearch.setText("Search");
            textSearch.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textSearch.setText("");
                }
            });

            GroupLayout searchFilterLayout = new GroupLayout(searchFilter);
            searchFilter.setLayout(searchFilterLayout);
            searchFilterLayout.setHorizontalGroup(
                    searchFilterLayout.createParallelGroup()
                            .addGroup(searchFilterLayout.createSequentialGroup()
                                    .addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)

                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buttonSearch, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(filter)
                                    .addContainerGap())
            );
            searchFilterLayout.setVerticalGroup(
                    searchFilterLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, searchFilterLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(searchFilterLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(filter, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                            .addComponent(textSearch, GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonSearch, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                    .addContainerGap()
                            ));
        }



        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(emailListHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollMails)
                        .addComponent(searchFilter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(emailListHeader, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollMails, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hyl
    private JPanel emailListHeader;

    private JPanel searchFilter;
    private JButton filter;
    private JTextField textSearch;

    private JScrollPane scrollMails;
    private JButton buttonSearch;
//    Search search;
    JFrame invisible = new JFrame();
    static BooksTable myBooksTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}