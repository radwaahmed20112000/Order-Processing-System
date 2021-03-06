package GUI;

import interfaces.IBook;
import models.Book;
import services.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class BooksTable extends JTable {
    //final Color blue = Color.decode(" ")
    JFrame invisible = new JFrame();
    //    Mail[] mails;
    Object[] colNames = {"", "Title", "Authors", " Publisher", "Year", "Price", "Category", "Quantity"};
    Object[][] data = {};
    DefaultTableModel dtm;
    List<IBook> bookList;

    public BooksTable(List<IBook> bookList) {
        this.bookList = bookList;
        buildGUI(bookList);
    }

    public void buildGUI(List<IBook> bookList) {
        this.bookList = bookList;
        setShowGrid(false);
        setBackground(Color.decode("#222831"));
        setForeground(Color.decode("#eeeeee"));
        getTableHeader().setReorderingAllowed(false);
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(false);
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionBackground(Color.decode("#c3edea"));
        setVisible(true);

        dtm = new DefaultTableModel(data, colNames);
        if (dtm.getRowCount() > 0) {
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }

        if(bookList == null){
            bookList = CustomerService.currentUser.getBooks();
            setModel(dtm);
        }
        if(bookList.size() == 0)
            return;
        String hello;
        int i =0;
            for (IBook Ibook:bookList
            ) {
                Book book = (Book)Ibook;
                dtm.addRow(new Object[]{"+", book.getTitle(), Arrays.toString(book.getAuthorsString()), book.getPublisher().getName(), book.getPublicationYear(), book.getSellingPrice(), book.getCategory(), book.getCurrentQuantity()});
            }


        TableColumn tc = getColumnModel().getColumn(0);
        tc.setCellEditor(
                new ButtonEditor(new JCheckBox()));
        tc.setCellRenderer(new ButtonRenderer());
        // tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
        tc.setMaxWidth(1);


        addMouseListener(new java.awt.event.MouseAdapter() {
                             @Override
                             public void mouseClicked(java.awt.event.MouseEvent evt) {
                                 int row = rowAtPoint(evt.getPoint());
                                 int col = columnAtPoint(evt.getPoint());
                                 if (row >= 0 && col >= 0) {
                                     if (getSelectedRow() >= 0) {
                                         JFrame frame = new JFrame();
//                            GUIMailPanel guiMailPanel = new GUIMailPanel(mails[getSelectedRow()]);
//                            frame.add(guiMailPanel.panel);
                                         System.out.println(row);
                                         frame.setSize(700, 700);
                                         frame.setTitle("Test Mail_panel");
                                         frame.setLocationRelativeTo(null);
                                         frame.setVisible(true);

                                     }
                                 }
                             }
                         }
        );

    }


}
class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;
        private int id = 100;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            id = row;
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            System.out.println(id);
            if (isPushed) {
                JOptionPane.showMessageDialog(button, label + ": Ouch!");
                // System.out.println(label + ": Ouch!");
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
