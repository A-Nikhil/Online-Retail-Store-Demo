package com.onlineretail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.*;

class login_shipper extends HttpServlet{
	String userid, password;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
    	// Operation
    }
	
	public static void main(String []args) {
		login_shipper obj =  new login_shipper();
		Statement stmt = null;
		try {
			stmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db").createStatement();
			stmt.execute("SELECT SUPPLIERID, PASSWORD FROM SUPPLIER");
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
			// System.out.println("DONE");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}