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

			PreparedStatement pStmt = connect.prepareStatement("select city from addressbook ;");
			ResultSet res = pStmt.executeQuery();

			int size = 0;
			while (res.next()) {
				size = size + 1;
			}
			System.out.println("size of column city is " + size);

			PreparedStatement pStmt1 = connect.prepareStatement("select state from addressbook ;");
			ResultSet res1 = pStmt1.executeQuery();

			int sizeState = 0;
			while (res1.next()) {
				sizeState = sizeState + 1;
			}
			System.out.println("size of column state is " + sizeState);

		} catch (

		SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}
	}

}
