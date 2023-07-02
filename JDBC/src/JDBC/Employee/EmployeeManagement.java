package Employee;
import java.sql.*;
public class EmployeeManagement {
	    static final String URL = "jdbc:mysql://localhost:3306/details";
	    static final String USER = "root";
	    static final String PASSWORD = "tiger";

	    

	    private static void readEmployeeRecords(Connection conn) throws SQLException {
	        String query = "SELECT * FROM employees";
	        
	        	Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            System.out.println("Employee records:");
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                int s = rs.getInt("salary");
	                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Salary: "+s);
	            }
	    }

	    private static void deleteEmployeeRecord(Connection conn, int employeeId) throws SQLException {
	        String query = "DELETE FROM employees WHERE id = ?";
	        
	        	PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setInt(1, employeeId);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Employee record deleted successfully");
	            } else {
	                System.out.println("No employee record found with ID: " + employeeId);
	            }
	        
	    }

	    private static void updateEmployeeRecord(Connection conn, int employeeId, String name, String email) throws SQLException {
	        String query = "UPDATE employees SET name = ?, email = ? WHERE id = ?";
	      
	            PreparedStatement stmt = conn.prepareStatement(query);
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
	    public static void main(String[] args) {
	        try {
	        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
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
	}


