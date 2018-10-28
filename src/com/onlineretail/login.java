package com.onlineretail;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class login extends HttpServlet {
	String userid, password;
    
	public void doPost() {
        userid = "thestarboy"; // req.getParameter("username");
        password = "ADMIN";    // req.getParameter("password");
        System.out.println(userid + " "+ password);
    }
    public static void main(String []args) {
    	login obj = new login();
        Connection c = null;
        Statement stmt = null;
        // HttpServletRequest req; HttpServletResponse res;
        obj.doPost();
        try {
            c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT USERID, PASSWORD FROM USERS;");
            while (rs.next()) {
            	String user = rs.getString(1);
                String pass = rs.getString(2);
            	System.out.println(user + " " + pass);
            	if(obj.userid.equals(rs.getString("USERID")) && obj.password.equals(rs.getString("PASSWORD"))) {
                    System.out.println("USER LOGIN SUCCESSFUL");
                 // Proceed further
                    break;
                } else {
                    System.out.println("USER LOGIN UNSUCCESSFUL");
                    // Same goes again
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
