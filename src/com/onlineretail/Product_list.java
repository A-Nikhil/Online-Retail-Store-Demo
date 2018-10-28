package com.onlineretail;

import java.sql.*;

import com.sun.corba.se.pept.transport.Connection;

public class Product_list {
	
	public void showProducts() {
		try {
			Statement stmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db").createStatement();
			ResultSet rs  = stmt.executeQuery("SELECT ITEMS.ITEMNAME, ITEMS.CATEGORY, ITEMS.PRICE, SUPPLIER.NAME FROM ITEMS INNER JOIN SUPPLIER ON ITEMS.SUPPLIERID = SUPPLIER.SUPPLIERID;");
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\n Category: " + rs.getString(2) + "\n Supplied By: " + rs.getString(4) + "\n Amount: " + rs.getInt(3));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String []args) {
		Product_list obj = new Product_list();
		obj.showProducts();
	}
}
