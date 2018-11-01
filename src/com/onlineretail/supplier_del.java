package com.onlineretail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class supplier_del extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
			String itemname = req.getParameter("itemname");
			Statement stmt = c.createStatement();
			ResultSet suppid = stmt.executeQuery("SELECT SUPPLIERID FROM CURRENTSUP;");
			int suppID = suppid.getInt(1);
			stmt.close();
			stmt = c.createStatement();
			System.out.println(suppID + " " + itemname);
			ResultSet rs = stmt.executeQuery("SELECT ITEMNAME FROM ITEMS WHERE SUPPLIERID = " + suppID + " AND ITEMNAME = \"" + itemname +"\";");
				if(rs.getString(1) == null) {
					System.out.println("NO ITEM");
					res.sendRedirect("supplier_page.jsp");
				} else {
					stmt.close();
					stmt = c.createStatement();
					stmt.execute("DELETE FROM ITEMS WHERE SUPPLIERID = " + suppID + " AND ITEMNAME = \"" + itemname +"\";");
					System.out.println("ITEM DELETED");
					res.sendRedirect("supplier_login.jsp");
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
