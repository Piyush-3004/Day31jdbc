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
			String cityOrState ="Nashik";
			PreparedStatement pStmt = connect.prepareStatement("select * from addressbook where city=? or state=?;");
			pStmt.setString(1,cityOrState);
			pStmt.setString(2,cityOrState);
			ResultSet res = pStmt.executeQuery();
			while(res.next()) {
				System.out.println(res.getString(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getString(4)+" "+res.getString(5)+" "+res.getInt(6)+" "+res.getInt(7)+" "+res.getString(8));
			}
				//	pStmt.execute();
			
			
			
		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}
	}

}
