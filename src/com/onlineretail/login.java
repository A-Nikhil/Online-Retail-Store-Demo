package com.onlineretail;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class login extends HttpServlet {
	String userid, password;
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
        userid = req.getParameter("username");
        password = req.getParameter("password");
        System.out.println(userid + " "+ password);
        
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	// Class.forName("com.sqlite.JDBC.Driver");
	    	c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
	        stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT USERID, PASSWORD FROM USERS;");
	        while (rs.next()) {
	        	String user = rs.getString(1);
	            String pass = rs.getString(2);
	            	//System.out.println(user + " " + pass);
	            	if(userid.equals(rs.getString("USERID")) && password.equals(rs.getString("PASSWORD"))) {
	                    System.out.println("USER LOGIN SUCCESSFUL");
	                    //RequestDispatcher rd = new RequestDispatcher();
	                    res.sendRedirect("user.html");
	                    break;
	                } else {
	                	res.sendRedirect("error.html");
	                }
	        }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
}

