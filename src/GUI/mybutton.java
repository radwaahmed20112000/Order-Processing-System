package GUI;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class mybutton extends JButton {
    mybutton(String s) {
        super(s);
        this.setBackground(new Color(153, 102, 128));
        this.setForeground(new Color(68, 50, 102));
        this.setMinimumSize(new Dimension(200, 50));
        this.setPreferredSize(new Dimension(200, 50));
    }
}
