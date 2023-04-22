package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;

import com.mysql.jdbc.Driver;

public class CheckvaluesInDB {

	public static void main(String[] args) throws SQLException{
		Connection con = null;
		String expectedvalue = "Balaji";
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TYMarch","root","root");
			Statement st = con.createStatement();
			String query = "select * from students;";
			ResultSet result = st.executeQuery(query);
			Boolean flag = false ;
			while(result.next())
			{
				String actualvalue = result.getString(2);
				if(actualvalue.equalsIgnoreCase(expectedvalue))
				{
					flag = true;
				}
			}
			if(flag==true)
			{
				System.out.println("Value is Present in DB");
			}
			else
			{
				System.out.println("Value is notPresent in DB");
			}
		Assert.assertTrue(flag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
