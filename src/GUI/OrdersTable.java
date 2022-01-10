package GUI;

import java.awt.*;
import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class OrdersTable extends JFrame {
    //final Color blue = Color.decode(" ")
    Object[] colNames = {"", "Book", "Count", " Date"};
    Object[][] data = {};
    DefaultTableModel dtm;
    public static int numberOfMails = 0;
    int page;
    JTable table;
    public OrdersTable(int page) {
        setSize(700, 700);
        setTitle("Pending orders");
        setLocationRelativeTo(null);
        setVisible(true);
        dtm = new DefaultTableModel(data,colNames);
        table = new JTable(dtm);
        TableColumn tc = table.getColumnModel().getColumn(0);
        tc.setCellEditor(
                new OrdersButtonEditor(new JCheckBox()));
        tc.setCellRenderer(new ButtonRenderer());

        // tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        this.page = page;
        buildGUI(page);
    }

    public void buildGUI(int page) {
        this.page = page;
        for (int i = 0; i < 8; i++) {
//                if(mail==null)
//                    break;
            dtm.addRow(new Object[]{"Accept", "Book" + i, "Count", " Date"});
            numberOfMails++;
        }
//        }


        setBackground(Color.decode("#222831"));
        setForeground(Color.decode("#eeeeee"));
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(Color.decode("#c3edea"));
        setVisible(true);
    }

    public int getNumberOfMails() {
        return numberOfMails;
    }

    public int getPage() {
        return page;
    }

    public static void main(String[] args) {
        OrdersTable o = new OrdersTable(1);
    }
}

class OrdersButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private final String label = "Accept";

    private boolean isPushed;

    public OrdersButtonEditor(JCheckBox checkBox) {
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
        button.setText("Accept");
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
