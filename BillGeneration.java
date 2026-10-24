// lab -2 Write a JAVA Program for generating Restaurant Bill 

import javax.swing.*;
import java.awt.event.*;

public class BillGeneration extends JFrame implements ActionListener 
{
    JCheckBox cb1 = new JCheckBox("Pizza @ 100");
    JCheckBox cb2 = new JCheckBox("Burger @ 30");
    JCheckBox cb3 = new JCheckBox("Tea @ 10");

    public BillGeneration() 
    {
        setLayout(null);

        add(new JLabel("Food Ordering System")).setBounds(50,50,200,20);

        cb1.setBounds(100,100,150,20);
        cb2.setBounds(100,150,150,20);
        cb3.setBounds(100,200,150,20);

        add(cb1); add(cb2); add(cb3);
        JButton b = new JButton("Order");
        b.setBounds(100,250,80,30);
        b.addActionListener(this);
        add(b);

        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) 
    {
        int total = 0;
        String msg = "";

        if(cb1.isSelected()){ total+=100; msg+="Pizza: 100\n"; }
        if(cb2.isSelected()){ total+=30;  msg+="Burger: 30\n"; }
        if(cb3.isSelected()){ total+=10;  msg+="Tea: 10\n"; }

        JOptionPane.showMessageDialog(this, msg+"----\nTotal: "+total);
    }

    public static void main(String[] args) {
        new BillGeneration();
    }
}

