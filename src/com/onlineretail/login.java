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
	String userid, password;
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
        userid = req.getParameter("username");
        password = req.getParameter("password");
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
	        stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT USERID, PASSWORD FROM USERS;");
	        while (rs.next()) {
	            	if(userid.equals(rs.getString("USERID")) && password.equals(rs.getString("PASSWORD"))) {
	                    stmt.execute("DROP TABLE CURRENT;");
	                    stmt.execute("CREATE TABLE CURRENT (USERID TEXT);");
	                    stmt.execute("INSERT INTO CURRENT (USERID) VALUES (\"" + userid + "\");");
	                    HttpSession session = req.getSession();
	                    session.setAttribute("userACName", userid);
	                    setDataForNext(session, stmt);
	                    res.sendRedirect("user.jsp");
	                    break;
	                } else {
	                	res.sendRedirect("error.html");
	                }
	        }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	public void setDataForNext(HttpSession session, Statement stmt) {
		try {
			ResultSet rs1  = stmt.executeQuery("SELECT ITEMS.ITEMNAME, ITEMS.CATEGORY, ITEMS.PRICE, SUPPLIER.NAME FROM ITEMS INNER JOIN SUPPLIER ON ITEMS.SUPPLIERID = SUPPLIER.SUPPLIERID;");
			
			String params[][] = new String[10][4];
			int i=0;
			while(rs1.next()) {
				params[i][0] = rs1.getString(1);
				params[i][1] = rs1.getString(2);
				params[i][2] = Integer.toString(rs1.getInt(3));
				params[i][3] = rs1.getString(4);
				i++;
			}
			System.out.println("fin");
			session.setAttribute("ProductList", params);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

