package com.onlineretail;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class supplier_signup extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		int contact = Integer.parseInt(req.getParameter("contact"));
		String password = req.getParameter("password");
		PreparedStatement pstmt = null;
		Statement getMaxId = null;
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
            getMaxId = c.createStatement();
            ResultSet rs1 = getMaxId.executeQuery("SELECT MAX(SUPPLIERID) FROM SUPPLIER");
            int suppid = rs1.getInt(1) + 1;
            getMaxId.close();
            c.close();
            c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
            pstmt = c.prepareStatement("INSERT INTO SUPPLIER (SUPPLIERID, NAME, CONTACT, PASSWORD) VALUES (?, ?, ?, ?);"); 
            pstmt.setInt(1, suppid);
            pstmt.setString(2, name);
            pstmt.setInt(3, contact);
            pstmt.setString(4, password);
            //Execute it
            pstmt.executeUpdate();
            System.out.println("SIGNUP SUCCESSFUL");
            res.sendRedirect("landing.html");
            pstmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
