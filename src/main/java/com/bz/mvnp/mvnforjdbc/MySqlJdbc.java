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
			stmt.execute("create table AddressBook (firstname varchar(50),lastname varchar(50),address varchar(100),city varchar(50),state varchar(50),zip integer,phonenumber integer,email varchar(100));");

		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}
	}

}
