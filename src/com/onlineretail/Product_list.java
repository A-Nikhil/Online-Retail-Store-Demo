package com.onlineretail;

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
		String userid = req.getParameter("userACName");
		try {
			PreparedStatement insertion = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db").prepareStatement("INSERT INTO CART (ITEMSID) VALUES (?);");
			String products[] = req.getParameterValues("Products");
			if (products != null) {
				for (String prod : products) {
					insertion.setInt(1, Integer.parseInt(prod));
					insertion.executeUpdate();
				}
			}
			Statement stmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db").createStatement();
			HttpSession session = req.getSession();
	        session.setAttribute("userACName", userid);
	        setDataForNext(session, stmt);
	        res.sendRedirect("user.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setDataForNext(HttpSession session, Statement stmt) {
		try {
			ResultSet rs1  = stmt.executeQuery("SELECT ITEMSID, ITEMS.ITEMNAME, ITEMS.PRICE FROM CART INNER JOIN ITEMS ON CART.ITEMSID = ITEMS.ITEMID;");
			
			String params[] = new String[5];
			int i=0;
			while(rs1.next()) {
				params[i] = rs1.getString(1);
			}
			System.out.println("fin");
			session.setAttribute("CartList", params);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
