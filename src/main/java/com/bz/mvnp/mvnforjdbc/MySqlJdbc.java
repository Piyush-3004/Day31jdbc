package com.bz.mvnp.mvnforjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

//import java.sql.*;
public class MySqlJdbc {

	public static void main(String[] args) throws SQLException {

		Scanner scanner = new Scanner(System.in);
		System.out.println(
				"Enter 1 to get elements sorted on firstnames of persons in City, 2 to identify by name and type , 3 to add to friend and family;");
		int ch = scanner.nextInt();
		if (ch == 1)
			sortByName();
		if (ch == 2)
			identifyByNameAndType();
		if (ch == 3)
			addToFriendFamily();

	}

	public static void addToFriendFamily() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter persom details ");
		System.out.println("Enter 1 to add to friend, 2 to add to family");
		int ch = scanner.nextInt();
		if (ch == 1) {
			System.out.println("Enter firstname");
			String firstname = scanner.next();
			System.out.println("Enter lastname");
			String lastname = scanner.next();
			System.out.println("Enter address");
			String addr = scanner.next();
			System.out.println("Enter city");
			String city = scanner.next();
			System.out.println("Enter state");
			String state = scanner.next();
			System.out.println("Enter zip");
			int zip = scanner.nextInt();
			System.out.println("Enter mobile");
			int mobile = scanner.nextInt();
			System.out.println("Enter eMail");
			String eMail = scanner.next();
			String type = "Friend";
			System.out.println("Enter Book name");
			String bookName = scanner.next();
			Connection connect = null;
			try {
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");

				PreparedStatement pStmt = connect.prepareStatement(
						"insert into addressbook (firstname,lastname,address,city,state,zip,phonenumber,email,type,name) values (?,?,?,?,?,?,?,?,?,?);");
				pStmt.setString(1, firstname);
				pStmt.setString(2, lastname);
				pStmt.setString(3, addr);
				pStmt.setString(4, city);
				pStmt.setString(5, state);
				pStmt.setInt(6, zip);
				pStmt.setInt(7, mobile);
				pStmt.setString(8, eMail);
				pStmt.setString(9, type);
				pStmt.setString(10, bookName);

				pStmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("unable to connect");
			} finally {
				connect.close();
			}

		}
		if (ch == 2) {
			System.out.println("Enter firstname");
			String firstname = scanner.next();
			System.out.println("Enter lastname");
			String lastname = scanner.next();
			System.out.println("Enter address");
			String addr = scanner.next();
			System.out.println("Enter city");
			String city = scanner.next();
			System.out.println("Enter state");
			String state = scanner.next();
			System.out.println("Enter zip");
			int zip = scanner.nextInt();
			System.out.println("Enter mobile");
			int mobile = scanner.nextInt();
			System.out.println("Enter eMail");
			String eMail = scanner.next();
			String type = "Family";
			System.out.println("Enter Book name");
			String bookName = scanner.next();

			Connection connect = null;
			try {
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");

				PreparedStatement pStmt = connect.prepareStatement(
						"insert into addressbook (firstname,lastname,address,city,state,zip,phonenumber,email,type,name) values(?,?,?,?,?,?,?,?,?,?);");
				pStmt.setString(1, firstname);
				pStmt.setString(2, lastname);
				pStmt.setString(3, addr);
				pStmt.setString(4, city);
				pStmt.setString(5, state);
				pStmt.setInt(6, zip);
				pStmt.setInt(7, mobile);
				pStmt.setString(8, eMail);
				pStmt.setString(9, type);
				pStmt.setString(10, bookName);

				pStmt.executeUpdate();

			} catch (SQLException e) {
				System.out.println("unable to connect");
			} finally {
				connect.close();
			}

		}
	}

	public static void countByType() throws SQLException {

		Scanner scanner = new Scanner(System.in);
		System.out.println("enter type to get count by type ");
		String type = scanner.next();

		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");

			PreparedStatement pStmt = connect.prepareStatement("select * from addressbook where type=?;");
			pStmt.setString(1, type);
			ResultSet res = pStmt.executeQuery();
			int count = 0;
			while (res.next()) {
				count += 1;
				System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
						+ res.getString(4) + " " + res.getString(5) + " " + res.getInt(6) + " " + res.getInt(7) + " "
						+ res.getString(8) + " " + res.getString(9) + " " + res.getString(10));
			}
			// pStmt.execute();

			System.out.println("no of elements in type are " + count);
		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}
	}

	public static void identifyByNameAndType() throws SQLException {

		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");

			Statement stmt = connect.createStatement();

			// Create colunm named type assign type as friend ////////////
//			stmt.execute("alter table addressbook add type varchar(50);");

			// Create colunm named name assign book as book1 ////////////
//			stmt.execute("alter table addressbook add name varchar(50);");

//			stmt.execute("update addressbook set type='Friend' where firstname = 'Piyush';");			

//			stmt.execute("update addressbook set name='Book1' where firstname = 'Piyush';");
			// stmt.execute();

			PreparedStatement pStmt = connect.prepareStatement("update addressbook set type=? where city = 'Nashik';");
			Scanner scanner = new Scanner(System.in);
			System.out.println("enter firstname to update type ");
			String name = scanner.next();
			pStmt.setString(1, name);
			System.out.println("enter type for " + name);
			String type = scanner.next();
			pStmt.setString(2, type);
			ResultSet res = pStmt.executeQuery();

			while (res.next()) {
				System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
						+ res.getString(4) + " " + res.getString(5) + " " + res.getInt(6) + " " + res.getInt(7) + " "
						+ res.getString(8) + " " + res.getString(9) + " " + res.getString(10));
			}

			System.out.println("enter book name to view the book");
			String bookName = scanner.next();
			PreparedStatement pStmt1 = connect.prepareStatement("select * from addressbook where name=?;");
			pStmt1.setString(1, bookName);
			ResultSet res1 = pStmt1.executeQuery();

			while (res1.next()) {
				System.out.println(res1.getString(1) + " " + res1.getString(2) + " " + res1.getString(3) + " "
						+ res1.getString(4) + " " + res1.getString(5) + " " + res1.getInt(6) + " " + res1.getInt(7)
						+ " " + res1.getString(8) + " " + res1.getString(9) + " " + res1.getString(10));
			}

			System.out.println("enter book type to view the book");
			String bookType = scanner.next();
			PreparedStatement pStmt2 = connect.prepareStatement("select * from addressbook where type=?;");
			pStmt2.setString(1, bookType);
			ResultSet res2 = pStmt2.executeQuery();

			while (res2.next()) {
				System.out.println(res2.getString(1) + " " + res2.getString(2) + " " + res2.getString(3) + " "
						+ res2.getString(4) + " " + res2.getString(5) + " " + res2.getInt(6) + " " + res2.getInt(7)
						+ " " + res2.getString(8) + " " + res2.getString(9) + " " + res2.getString(10));
			}

		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}

	}

	public static void sortByName() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter city to sort by name");
		scanner.next();
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");
//			Statement stmt = connect.createStatement();
			String city = "Nashik";
			PreparedStatement pStmt = connect
					.prepareStatement("select * from addressbook where city=? order by firstname;");
			pStmt.setString(1, city);
			ResultSet res = pStmt.executeQuery();
			while (res.next()) {
				System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
						+ res.getString(4) + " " + res.getString(5) + " " + res.getInt(6) + " " + res.getInt(7) + " "
						+ res.getString(8));
			}
			// pStmt.execute();

		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}

	}

}
