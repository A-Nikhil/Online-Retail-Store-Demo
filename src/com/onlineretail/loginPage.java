package com.onlineretail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginPage {
	public void login() {
		String userid = "thestarboy";
		String password = "PASSWORD";
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
	        stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT USERID, PASSWORD FROM USERS;");
	        while (rs.next()) {
	        	String user = rs.getString(1);
	            String pass = rs.getString(2);
	            	System.out.println(user + " " + pass);
	            	if(userid.equals(rs.getString("USERID")) && password.equals(rs.getString("PASSWORD"))) {
	                    System.out.println("USER LOGIN SUCCESSFUL");
	                 // Proceed further
	                    break;
	                } else {
	                    System.out.println("USER LOGIN UNSUCCESSFUL");
	                    // Same goes again
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
}