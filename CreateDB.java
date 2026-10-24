import java.sql.*;

public class CreateDB {
    public static void main(String[] args) {
        String url = "jdbc:ucanaccess://ICE_PUST.accdb;newdatabaseversion=V2010";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                "CREATE TABLE Student (Name VARCHAR(50), Email VARCHAR(100), Phone VARCHAR(20))");
            System.out.println("Database created successfully!");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

// This is for database code
//javac CreateDB.java
//java -cp ".;lib\*" CreateDB

// this is for main code
//javac StudentAccessDB.java
//java -cp ".;lib\*" StudentAccessDB

// driver link
// https://ucanaccess.sourceforge.net/