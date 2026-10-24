// Lab 12: Write a Java program to perform insert, delete, edit and show data using MySQL database.

import java.sql.*;
import java.util.Scanner;

public class MySQLCRUD {
    // Database connection details
    static final String DB_URL = "jdbc:mysql://localhost:3306/ice_db?useSSL=false&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "";

    // 1. Insert Data (Create)
    public static void insertRecord(int id, String name, String email, String phone) {
        String sql = "INSERT INTO students (id, name, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Insert Error: " + e.getMessage());
        }
    }

    // 2. Show Data (Read)
    public static void showRecords() {
        String sql = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n----------------- All Student Records -----------------");
            System.out.printf("%-6s %-20s %-25s %-15s\n", "ID", "Name", "Email", "Phone");
            System.out.println("-------------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                System.out.printf("%-6d %-20s %-25s %-15s\n", id, name, email, phone);
            }
            if (!found) {
                System.out.println("No records found in database.");
            }
            System.out.println("-------------------------------------------------------\n");

        } catch (SQLException e) {
            System.err.println("Show Error: " + e.getMessage());
        }
    }

    // 3. Edit Data (Update)
    public static void editRecord(int id, String newEmail, String newPhone) {
        String sql = "UPDATE students SET email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setString(2, newPhone);
            pstmt.setInt(3, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record updated successfully for Student ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Edit Error: " + e.getMessage());
        }
    }

    // 4. Delete Data (Delete)
    public static void deleteRecord(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record deleted successfully for Student ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Delete Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   MySQL Database CRUD Operations in Java JDBC    ");
        System.out.println("==================================================");

        // 1. Show initial records
        System.out.println("\n--- Step 1: Show Records ---");
        showRecords();

        // 2. Insert records
        System.out.println("\n--- Step 2: Insert New Records ---");
        insertRecord(101, "Rahim Ahmed", "rahim@pust.ac.bd", "01700112233");
        insertRecord(102, "Karim Ullah", "karim@pust.ac.bd", "01800223344");

        // 3. Show updated records
        System.out.println("\n--- Step 3: Show Records After Insertion ---");
        showRecords();

        // 4. Edit a record
        System.out.println("\n--- Step 4: Edit Student Record (ID: 101) ---");
        editRecord(101, "rahim.updated@pust.ac.bd", "01799887766");

        // 5. Show records after edit
        System.out.println("\n--- Step 5: Show Records After Edit ---");
        showRecords();

        // 6. Delete a record
        System.out.println("\n--- Step 6: Delete Student Record (ID: 102) ---");
        deleteRecord(102);

        // 7. Show final records
        System.out.println("\n--- Step 7: Final Records After Deletion ---");
        showRecords();
    }
}

//cd c:\3.2\java\source_code

//javac MySQLCRUD.java

//java -cp ".;lib\*" MySQLCRUD



/*CREATE DATABASE ice_db;
USE ice_db;
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20)
);    */


// driver link
// https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar