package com.onlineretail;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Product_list extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
			Statement stmt = c.createStatement();
			ResultSet User = stmt.executeQuery("SELECT USERID FROM CURRENT");
			String userid = User.getString(1);
			stmt.close();
			String products[] = req.getParameterValues("Products");
			stmt = c.createStatement();
    		stmt.execute("DROP TABLE CART;");
    	    stmt.close();
    	    System.out.println("TABLE DROPPED");
    	    stmt = c.createStatement();
    	    stmt.execute("CREATE TABLE CART (ITEMSID TEXT);");
    	    stmt.close();
    	    System.out.println("TABLE CREATED");
			PreparedStatement insertion = null;
				for (int i = 0; i<products.length ; i++) {
					System.out.println(products[i]);
					insertion = c.prepareStatement("INSERT INTO CART (ITEMSID) VALUES (?);");
					insertion.setInt(1, Integer.parseInt(products[i]));
					System.out.println("INSERT INTO CART (ITEMSID) VALUES (" + products[i] + ");");
					insertion.executeUpdate();
				}
			insertion.close();
			HttpSession session = req.getSession();
	        session.setAttribute("userACName", userid);
	        stmt = c.createStatement();
	        ResultSet rs1  = stmt.executeQuery("SELECT ITEMSID, ITEMS.ITEMNAME, ITEMS.PRICE FROM CART INNER JOIN ITEMS ON CART.ITEMSID = ITEMS.ITEMID;");			
			String params[][] = new String[3][3];
			int i=0;
			while(rs1.next()) {
				params[i][0] = rs1.getString(1);
				params[i][1] = rs1.getString(2);
				params[i][2] = Integer.toString(rs1.getInt(3));
				i++;
			}
			System.out.println("fin");
			session.setAttribute("CartList", params);
	        
			stmt.close();
			
	        res.sendRedirect("cart.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
