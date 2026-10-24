// Lab 11: Java Database Connectivity with MS Access Database (ICE_PUST)
// Problem: 
// Suppose we have an MS Access database named ICE_PUST which has a table named 
// by Student with field (Name, Email, and Phone). 
// a. Write down a java program to insert data into the Student table of the ICE_PUST database.
// b. Create a java program to print all student records from the Student table of the ICE_PUST database.

import java.sql.*;

public class StudentAccessDB {
    // Connection URL for MS Access Database
    // Using UCanAccess driver (Modern Type-4 Driver for MS Access):
    static final String DB_URL = "jdbc:ucanaccess://ICE_PUST.accdb";
    
    // Legacy JDBC-ODBC Bridge alternative:
    // static final String DB_URL = "jdbc:odbc:ICE_PUST";

    // a. Method to insert data into Student table
    public static void insertStudent(String name, String email, String phone) {
        String sql = "INSERT INTO Student (Name, Email, Phone) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student record was inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
        }
    }

    // b. Method to print all student records from Student table
    public static void displayAllStudents() {
        String sql = "SELECT Name, Email, Phone FROM Student";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n----------------- Student Records (ICE_PUST) -----------------");
            System.out.printf("%-20s %-30s %-15s\n", "Name", "Email", "Phone");
            System.out.println("--------------------------------------------------------------");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                System.out.printf("%-20s %-30s %-15s\n", name, email, phone);
            }

            if (!hasData) {
                System.out.println("No records found in the Student table.");
            }
            System.out.println("--------------------------------------------------------------\n");

        } catch (SQLException e) {
            System.err.println("Error retrieving student records: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MS Access Database Management (ICE_PUST) ===");

        // a. Insert data into Student table
        System.out.println("\n[Action] Inserting student data into ICE_PUST...");
        insertStudent("Avishek Roy", "avishek@pust.ac.bd", "01711000000");
     insertStudent("Tanvir Ahmed", "tanvir@pust.ac.bd", "01811000000");

        // b. Print all student records from Student table
        System.out.println("\n[Action] Printing all student records from ICE_PUST...");
        displayAllStudents();
    }
}
