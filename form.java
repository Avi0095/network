// lab 3 Write a JAVA Program to Create a Student form in GUI 
import javax.swing.*;
import java.awt.event.*;

public class form implements ActionListener 
{
    JFrame f = new JFrame();
    JTextField t1 = new JTextField("Enter Your Name");
    JTextField t2 = new JTextField("Enter Your Roll");
    JTextField t3 = new JTextField("Enter Your Department");
    JLabel success = new JLabel();

    public form() 
    {
        JPanel p = new JPanel(null);
        f.setSize(400,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p);

        p.add(new JLabel("Name")).setBounds(10,10,80,25);
        p.add(new JLabel("Roll")).setBounds(10,60,80,25);
        p.add(new JLabel("Department")).setBounds(10,110,80,25);

        t1.setBounds(100,10,200,25);
        t2.setBounds(100,60,200,25);
        t3.setBounds(100,110,200,25);

        p.add(t1); p.add(t2); p.add(t3);

        JButton b = new JButton("Save");
        b.setBounds(150,160,80,25);
        b.addActionListener(this);
        p.add(b);

        success.setBounds(130,210,200,25);
        p.add(success);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        success.setText("Saved Successfully");
    }

    public static void main(String[] args) {
        new form();
    }
}

