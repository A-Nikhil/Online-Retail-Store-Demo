package com.onlineretail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class Thingstodo {
	/*
	 * SUPPLIER_ADD
	 * SUPPLIER_REMOVE
	 * PRODUCT_LIST
	 * CART
	 * FINAL_ORDER
	 */
	
	
	public static void main(String []args) {
		try {
		Connection c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
        Statement stmt = c.createStatement();
		ResultSet rs1  = stmt.executeQuery("SELECT ITEMS.ITEMNAME, ITEMS.CATEGORY, ITEMS.PRICE, SUPPLIER.NAME, ITEMS.DESCRIPTION, ITEMS.ITEMID FROM ITEMS INNER JOIN SUPPLIER ON ITEMS.SUPPLIERID = SUPPLIER.SUPPLIERID;");
		String params[][] = new String[10][6];
		int i=0;
		while(rs1.next()) {
			params[i][0] = rs1.getString(1);
			params[i][1] = rs1.getString(2);
			params[i][2] = Integer.toString(rs1.getInt(3));
			params[i][3] = rs1.getString(4);
			params[i][4] = rs1.getString(5);
			params[i][5] = Integer.toString(rs1.getInt(6));
			i++;
		}
		
		for(i=0; i<7; i++) {
			for(int j=0; j<6; j++) {
				System.out.print(params[i][j] +  " ");
			}
			System.out.println();
		}
		stmt.close();
		c.close();
	} catch (Exception e) { System.out.println(e.getMessage()); }
	}
}
