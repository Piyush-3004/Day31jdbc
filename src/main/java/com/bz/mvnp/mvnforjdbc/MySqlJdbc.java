package com.bz.mvnp.mvnforjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MySqlJdbc {

	public static void main(String[] args) throws SQLException {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");
			Statement stmt = connect.createStatement();
			stmt.execute("insert into addressbook values ('Piyush' ,'patil','shalimar','nashik','maharashtra',422001,901155747,'piyush@gmail.com');");

		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}
	}

}
