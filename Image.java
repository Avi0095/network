// lab_1: Write a JAVA Program to Display Image using JFrame

import javax.swing.*; // Import Swing classes like JFrame, JLabel, ImageIcon
import java.awt.*; // Import AWT classes for layout and GUI components

// Create a class named Image that extends JFrame
public class Image extends JFrame {

    // Constructor of the Image class
    Image() {

        setLayout(new FlowLayout());  // Set the layout manager to FlowLayout
        add(new JLabel(new ImageIcon("C:\\3.2\\java\\practise\\ice.jpg")));
        add(new JLabel(new ImageIcon("C:\\3.2\\java\\practise\\pust.png")));

        setTitle("Image Program");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Close the application when the window is closed

        pack();    // Adjust the frame size according to components
  setVisible(true);       // Make the frame visible
    }


    public static void main(String[] args) {     // Main method: program execution starts here

 
        new Image();        // Create an object of Image class
    }
}

