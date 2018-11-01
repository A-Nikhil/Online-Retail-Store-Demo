package com.onlineretail;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class login extends HttpServlet {
	String userid, password, username;
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		userid = req.getParameter("username");
        password = req.getParameter("password");
		Connection c = null;
	    Statement stmt = null;
	    boolean found = false;
	    try {
	    	ResultSet rs;
	    	c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
	        stmt = c.createStatement();
	        rs = stmt.executeQuery("SELECT USERID, PASSWORD FROM USERS;");
	        	while (rs.next()) {
	        		String USERNAME = rs.getString(1);
	        		String PASSWORD = rs.getString(2);
	        		System.out.println(USERNAME + " " + PASSWORD);
	            		if(userid.equals(USERNAME) && password.equals(PASSWORD)) {
	            			found = true;
	            			break;
	            		}
	        	}
	        	stmt.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    try {
	    	if(found) {
	    		stmt = c.createStatement();
	    		stmt.execute("DROP TABLE CURRENT;");
	    	    stmt.close();
	    	    System.out.println("TABLE DROPPED");
	    	    
	    	    stmt = c.createStatement();
	    	    stmt.execute("CREATE TABLE CURRENT (USERID TEXT);");
	    	    stmt.close();
	    	    System.out.println("TABLE CREATED");
	    	    
	    	    stmt = c.createStatement();
	    	    ResultSet name = stmt.executeQuery("SELECT FNAME FROM USERS WHERE USERID = (\"" + userid + "\");");
	    	    username = name.getString(1);
	    	    stmt.close();
	    	    System.out.println("VALUE ENTERED");
	    	    
	    	    stmt = c.createStatement();
	    	    stmt.execute("INSERT INTO CURRENT (USERID) VALUES (\"" + username + "\");");
	    	    stmt.close();
	    	    System.out.println("VALUE ENTERED");
	    	   
	    	    stmt = c.createStatement();
	    	    HttpSession session = req.getSession();
	    	    session.setAttribute("userACName", username);
	    	    
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
				if(i==9) {
					params[9][0] = "OUT OF STOCK";
					params[9][1] = "N/A";
					params[9][2] = "N/A";
					params[9][3] = "N/A";
					params[9][4] = "N/A";
					params[9][5] = "N/A";
				}
				
				System.out.println("fin");
				session.setAttribute("ProductList", params);
	    	     
	    	    stmt.close();
	    	    c.close();
	    	    res.sendRedirect("product_page.jsp");
	    	} else {
	    		res.sendRedirect("error.html");
	    	}
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	}
}