
// Lab 4: Simple Calculator with Colorful GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculator extends JFrame implements ActionListener {

    JTextField res = new JTextField();

    double n1, n2;
    char op = '\0';
    boolean startNewNumber = true;

    public calculator() {

        // Window settings
        setTitle("simple Calculator");
        setLayout(new BorderLayout(8, 8));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Calculator background
        getContentPane().setBackground(new Color(30, 35, 50));

        // Display settings
        res.setFont(new Font("Arial", Font.BOLD, 30));
        res.setHorizontalAlignment(JTextField.RIGHT);
        res.setPreferredSize(new Dimension(300, 75));
        res.setBackground(new Color(240, 245, 255));
        res.setForeground(new Color(25, 35, 60));
        res.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        // Button panel
        JPanel p = new JPanel(new GridLayout(5, 4, 8, 8));
        p.setBackground(new Color(30, 35, 50));
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        String btns[] = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        // Create buttons
        for (String s : btns) {

            JButton b = new JButton(s);

            b.setFont(new Font("Arial", Font.BOLD, 22));
            b.setFocusPainted(false);
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Number buttons
            b.setBackground(new Color(65, 75, 100));
            b.setForeground(Color.WHITE);

            // Operator buttons
            if ("+-*/".contains(s) && s.length() == 1) {
                b.setBackground(new Color(255, 165, 60));
                b.setForeground(Color.BLACK);
            }

            // Equals button
            if (s.equals("=")) {
                b.setBackground(new Color(35, 190, 120));
                b.setForeground(Color.WHITE);
            }

            // Clear button
            if (s.equals("C")) {
                b.setBackground(new Color(230, 65, 80));
                b.setForeground(Color.WHITE);
            }

            b.addActionListener(this);
            p.add(b);
        }

        // Add components
        add(res, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);

        // Window size
        setSize(360, 470);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String s = ((JButton) e.getSource()).getText();

        // Clear calculator
        if (s.equals("C")) {
            res.setText("");
            n1 = 0;
            n2 = 0;
            op = '\0';
            startNewNumber = true;
            return;
        }

        // Number buttons
        if (s.length() == 1 && s.charAt(0) >= '0'
                && s.charAt(0) <= '9') {

            if (startNewNumber) {
                res.setText(s);
                startNewNumber = false;
            } else {
                res.setText(res.getText() + s);
            }
            return;
        }

        // Decimal point
        if (s.equals(".")) {
            if (startNewNumber) {
                res.setText("0.");
                startNewNumber = false;
            } else if (!res.getText().contains(".")) {
                res.setText(res.getText() + ".");
            }
            return;
        }

        // Arithmetic operators
        if (s.length() == 1 && "+-*/".contains(s)) {

            try {
                if (res.getText().isEmpty()) {
                    return;
                }

                // Allow changing the operator before entering
                // the next number
                if (op != '\0' && startNewNumber) {
                    op = s.charAt(0);
                    return;
                }

                n2 = Double.parseDouble(res.getText());

                // Calculate the previous operation first
                if (op != '\0') {
                    n1 = calculate(n1, n2, op);
                    res.setText(formatResult(n1));
                } else {
                    n1 = n2;
                }

                // Store the new operator
                op = s.charAt(0);
                startNewNumber = true;

            } catch (ArithmeticException ex) {
                error("Cannot divide by zero");
            } catch (Exception ex) {
                error("Invalid input");
            }

            return;
        }

        // Calculate final result
        if (s.equals("=")) {

            try {
                if (op == '\0' || startNewNumber
                        || res.getText().isEmpty()) {
                    return;
                }

                n2 = Double.parseDouble(res.getText());

                double result = calculate(n1, n2, op);

                res.setText(formatResult(result));

                n1 = result;
                op = '\0';
                startNewNumber = true;

            } catch (ArithmeticException ex) {
                error("Cannot divide by zero");
            } catch (Exception ex) {
                error("Invalid input");
            }
        }
    }

    // Perform arithmetic operations
    double calculate(double a, double b, char operator) {

        switch (operator) {
            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                if (b == 0) {
                    throw new ArithmeticException();
                }
                return a / b;

            default:
                return b;
        }
    }

    // Remove unnecessary decimal zeros
    String formatResult(double value) {
        if (Double.isFinite(value)
                && value == Math.rint(value)
                && Math.abs(value) < 1.0e16) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    // Display errors
    void error(String msg) {
        JOptionPane.showMessageDialog(this, msg);
        res.setText("");
        n1 = 0;
        n2 = 0;
        op = '\0';
        startNewNumber = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new calculator());
    }
}