package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class BooksTable extends JTable {
    //final Color blue = Color.decode(" ")
    JFrame invisible = new JFrame();
    //    Mail[] mails;
    Object[] colNames = {"", "Title", "Authors", " Publisher", "Year", "Price", "Category", "Quantity"};
    Object[][] data = {};
    DefaultTableModel dtm;
    public static int numberOfMails = 0;
    int page;

    public BooksTable(int page) {
        this.page = page;
        buildGUI(page);
    }

    public void buildGUI(int page) {
        this.page = page;
        dtm = new DefaultTableModel(data, colNames);

        // clearTable();
//        if(mails!=null)
//            Arrays.fill(mails,null);
        setModel(dtm);
//        App myApp = new App();
//        mails = (Mail[]) myApp.listEmails(page);
//        if(mails!=null) {
        for (int i = 0; i < 8; i++) {
//                if(mail==null)
//                    break;
            dtm.addRow(new Object[]{"+", "Title" + i, "Authors" + i, " Publisher", "Year", "Price", "Category", "Quantity"});
            numberOfMails++;
        }
//        }


        setBackground(Color.decode("#222831"));
        setForeground(Color.decode("#eeeeee"));
        TableColumn tc = getColumnModel().getColumn(0);
        tc.setCellEditor(
                new ButtonEditor(new JCheckBox()));
        tc.setCellRenderer(new ButtonRenderer());
        // tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
        tc.setMaxWidth(1);
        setShowGrid(false);
        getTableHeader().setReorderingAllowed(false);
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(false);
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionBackground(Color.decode("#c3edea"));
        setVisible(true);

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

    public int getNumberOfMails() {
        return numberOfMails;
    }

    public int getPage() {
        return page;
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

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
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
            if (isPushed) {
                //
                //
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
