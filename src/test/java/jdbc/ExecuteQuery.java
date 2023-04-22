package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteQuery {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TYMarch","root","root");
			Statement st = con.createStatement();
			String query = "select * from students;";
			ResultSet result = st.executeQuery(query);
			while(result.next())
			{
				System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}

	}

}
