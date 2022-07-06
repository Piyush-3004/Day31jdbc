package com.bz.mvnp.mvnforjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class MySqlJdbc {

	public static void main(String[] args) throws SQLException {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");
//			Statement stmt = connect.createStatement();
			PreparedStatement pStmt = connect.prepareStatement("delete from addressbook where firstname = ?");
			String name ="Piyush";
			pStmt.setString(1, name);
			pStmt.execute();
		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}
	}

}
