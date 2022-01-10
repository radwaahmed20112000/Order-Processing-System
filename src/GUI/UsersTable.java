package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class UsersTable extends JFrame {
    //final Color blue = Color.decode(" ")
    Object[] colNames = {"", "Email", "First Name", "Last Name"};
    Object[][] data = {};
    DefaultTableModel dtm;
    public static int numberOfMails = 0;
    int page;
    JTable table;
    public UsersTable(int page) {
        setSize(700, 700);
        setTitle("Users");
        setLocationRelativeTo(null);
        setVisible(true);
        dtm = new DefaultTableModel(data,colNames);
        table = new JTable(dtm);
        TableColumn tc = table.getColumnModel().getColumn(0);
        tc.setCellEditor(
                new UsersButtonEditor(new JCheckBox()));
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
            dtm.addRow(new Object[]{"Promote", "Email" + i, "First Name", "Last Name"});
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

    public static void main(String[] args) {
        UsersTable o = new UsersTable(1);
    }
}

class UsersButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private final String label = "Accept";

    private boolean isPushed;

    public UsersButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
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
