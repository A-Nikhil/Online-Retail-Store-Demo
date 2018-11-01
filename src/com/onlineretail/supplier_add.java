package com.onlineretail;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class supplier_add extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
        String itemname = req.getParameter("itemname");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        int price = Integer.parseInt(req.getParameter("price"));
        try {
        	Connection c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
            stmt = c.createStatement();
            ResultSet ITEM = stmt.executeQuery("SELECT MAX(ITEMID) FROM ITEMS"); 
            int itemid = ITEM.getInt(1) + 1;
            stmt.close();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUPPLIERID FROM CURRENTSUP"); 
            int suppid = rs.getInt(1);
            stmt.close();
            pstmt = c.prepareStatement("INSERT INTO ITEMS (ITEMID, ITEMNAME, DESCRIPTION, CATEGORY, PRICE, SUPPLIERID) VALUES (?, ?, ?, ?, ?, ?);");
            pstmt.setInt(1, itemid);
            pstmt.setString(2, itemname);
            pstmt.setString(3, description);
            pstmt.setString(4, category);
            pstmt.setInt(5, price);
            pstmt.setInt(6, suppid);
            System.out.println("Updated");
            //Execute it
            pstmt.executeUpdate();
            pstmt.close();
            c.close();
            res.sendRedirect("supplier_page.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
