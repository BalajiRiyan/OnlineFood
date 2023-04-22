package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class UpdateQuery {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TYMarch","root","root");
			Statement st = con.createStatement();
			String query = "Alter table students add company2 varchar(15);";
			int result = st.executeUpdate(query);
			if(result !=0)
			{
				System.out.println("Data Inserted");
			}
			else
			{
				System.out.println("Data not inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
