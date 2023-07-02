package JDBC;

import java.sql.*;
public class EmployeeManagement {
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db";
	    private static final String DB_USER = "your_username";
	    private static final String DB_PASSWORD = "your_password";

	    public static void main(String[] args) {
	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            System.out.println("Connected to the database");

	            // Read employee records
	            readEmployeeRecords(conn);

	            // Delete an employee record
	            deleteEmployeeRecord(conn, 3);

	            // Update an employee record
	            updateEmployeeRecord(conn, 2, "John Doe", "john.doe@example.com");

	            // Read employee records after update and deletion
	            readEmployeeRecords(conn);

	        } catch (SQLException e) {
	            System.err.println("Database connection error: " + e.getMessage());
	        }
	    }

	    private static void readEmployeeRecords(Connection conn) throws SQLException {
	        String query = "SELECT * FROM employees";
	        try (Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            System.out.println("Employee records:");
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
	            }
	        }
	    }

	    private static void deleteEmployeeRecord(Connection conn, int employeeId) throws SQLException {
	        String query = "DELETE FROM employees WHERE id = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, employeeId);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Employee record deleted successfully");
	            } else {
	                System.out.println("No employee record found with ID: " + employeeId);
	            }
	        }
	    }

	    private static void updateEmployeeRecord(Connection conn, int employeeId, String name, String email) throws SQLException {
	        String query = "UPDATE employees SET name = ?, email = ? WHERE id = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, name);
	            stmt.setString(2, email);
	            stmt.setInt(3, employeeId);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Employee record updated successfully");
	            } else {
	                System.out.println("No employee record found with ID: " + employeeId);
	            }
	        }
	    }
	}

}
