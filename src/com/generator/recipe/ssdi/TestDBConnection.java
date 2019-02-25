package com.generator.recipe.ssdi;

import java.sql.*;

public class TestDBConnection {

	static Connection con;

	public static void main(String[] args) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/RecipeGeneratorDB", "root", "password");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from recipegeneratordb.test");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "----" + rs.getString(2));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
}
