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
		System.out.println("Enter 6 to Retrive Persons From CityOrState, 7 to get sizeby city and state , "
				+ " 8 sort on name based on city ,9 to identify book by name and type ,10 to get count by type ,"
				+ " 11 to add to both friend and famili ," + " 13 try operations with another table");
		int ch = scanner.nextInt();
		if (ch == 6)
			retrivePersonsFromCityOrState();
		if (ch == 7)
			getCountFromCityOrState();
		if (ch == 8)
			sortByNameForCity();
		if (ch == 9)
			identifyByNameAndType();
		if (ch == 10)
			getCountByType();
		if (ch == 11)
			addToFriendAndFamily();
		if (ch == 13)
			newTable();

	}

	///////////////////// Uc 13 //////////////////////////////
	public static void newTable() throws SQLException {
		try {

			Scanner scanner = new Scanner(System.in);
			System.out.println("We have 2 tables addressbook and addressbook2 select te table after entering below details:"); 
			System.out.println(
					"Enter 6 to retrivePersonsFromCityOrState, 7 getCountFromCityOrState,8 to sortByNameForCity,10 to getCountByType()");
			int ch = scanner.nextInt();
			if(ch==6)
			retrivePersonsFromCityOrState();
			else if(ch==7)
			getCountFromCityOrState();
			else if(ch==8)
			sortByNameForCity();
			else if(ch==10)
			getCountByType();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

//////////////////////////////////   Uc 11  ////////////////////////////
	public static void addToFriendAndFamily() throws SQLException {
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

//////////////////////////Uc 10  /////////////////////////////	
	public static void getCountByType() throws SQLException {

		Connection connect = null;
		try {

			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter table name , 1st table addressbook 2ndtable addressbook2 ");
			String tablename = scanner.next();

			System.out.println("Enter Type");
			String type = scanner.next();
			PreparedStatement pStmt = connect.prepareStatement("select count(type) from ? where type=?;");

			pStmt.setString(1, tablename);
			pStmt.setString(2, type);
			ResultSet res = pStmt.executeQuery();
			res.next();
			System.out.println(res.getInt("count(type)"));

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			connect.close();
		}

	}

/////////////////////////////////  Uc 9  ////////////////////////////	
	public static void identifyByNameAndType() throws SQLException {

		Connection connect = null;
		try {
			Scanner scanner = new Scanner(System.in);
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");

			Statement stmt = connect.createStatement();

			// Create colunm named type assign type as friend ////////////
//			stmt.execute("alter table addressbook add type varchar(50);");

			// Create colunm named name assign book as book1 ////////////
//			stmt.execute("alter table addressbook add name varchar(50);");

//			stmt.execute("update addressbook set type='Friend' where firstname = 'Piyush';");			

//			stmt.execute("update addressbook set name='Book1' where firstname = 'Piyush';");
			// stmt.execute();

			System.out.println("enter name of book , book0 or book1");
			String name = scanner.next();

			System.out.println("enter type for " + name);
			String type = scanner.next();
			PreparedStatement pStmt = connect.prepareStatement("select * from addressbook where name=? and type=? ");
			pStmt.setString(1, name);
			pStmt.setString(2, type);
			ResultSet res = pStmt.executeQuery();

			while (res.next()) {
				System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
						+ res.getString(4) + " " + res.getString(5) + " " + res.getInt(6) + " " + res.getInt(7) + " "
						+ res.getString(8) + " " + res.getString(9) + " " + res.getString(10));
			}

		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}

	}

//////////////////////////Uc 8 //////////////////////////////////
	public static void sortByNameForCity() throws SQLException {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter table name , 1st table addressbook 2ndtable addressbook2 ");
			String tablename = scanner.next();

			System.out.println("Enter city or State");
			String cityOrState = scanner.next();
			PreparedStatement pStmt = connect
					.prepareStatement("select * from ? where city=? or state=? order by firstname;");
			pStmt.setString(1, tablename);
			pStmt.setString(2, cityOrState);
			pStmt.setString(3, cityOrState);
			ResultSet res = pStmt.executeQuery();
			while (res.next()) {
				System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
						+ res.getString(4) + " " + res.getString(5) + " " + res.getInt(6) + " " + res.getInt(7) + " "
						+ res.getString(8));
			}

		} catch (SQLException e) {
			System.out.println("unable to connect");
		} finally {
			connect.close();
		}
	}

///////////////////////  Uc 7  //////////////////////////////
	public static void getCountFromCityOrState() throws SQLException {
		Connection connect = null;
		try {

			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter table name , 1st table addressbook 2ndtable addressbook2 ");
			String tablename = scanner.next();

			System.out.println("Enter city or State");
			String cityOrState = scanner.next();
			PreparedStatement pStmt = connect.prepareStatement("select count(city) from ? where city =?;");
			pStmt.setString(1, tablename);
			pStmt.setString(2, cityOrState);
			ResultSet res = pStmt.executeQuery();
			res.next();
			System.out.println(res.getInt("count(city)"));

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			connect.close();
		}
	}

/////////////////////// Uc 6 //////////////////////////////
	public static void retrivePersonsFromCityOrState() throws SQLException {
		Connection connect = null;

		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBookService", "root", "root");
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter table name , 1st table addressbook 2ndtable addressbook2 ");
			String tablename = scanner.next();

			System.out.println("Enter city or State");
			String cityOrState = scanner.next();
			PreparedStatement pStmt = connect.prepareStatement("select * from ? where city =? or state=?;");
			pStmt.setString(1, tablename);
			pStmt.setString(2, cityOrState);
			pStmt.setString(3, cityOrState);

			ResultSet res = pStmt.executeQuery();
			while (res.next()) {
				System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
						+ res.getString(4) + " " + res.getString(5) + " " + res.getInt(6) + " " + res.getInt(7) + " "
						+ res.getString(8) + " " + res.getString(9) + " " + res.getString(10));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
