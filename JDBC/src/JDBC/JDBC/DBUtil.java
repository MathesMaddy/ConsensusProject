package JDBC;
import java.sql.*;

public class DBUtil {
	public static void main(String args[]) throws Exception{
		String str="jdbc:mysql://localhost:3306/mathes";
		
		try (Connection con=DriverManager.getConnection(str,"root","tiger")){
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement st=con.createStatement();
			String s="select * from empdetail";
			ResultSet rt= st.executeQuery(s);while(rt.next()) {
			System.out.println(rt.getInt(1));
			System.out.println(rt.getString(2));
			System.out.println(rt.getInt(3));
			}
		
	}
	
	}
}

