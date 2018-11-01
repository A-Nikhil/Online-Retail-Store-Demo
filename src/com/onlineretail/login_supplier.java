package com.onlineretail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class login_supplier extends HttpServlet{
	String userid, password;
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		userid = req.getParameter("userid");
		password = req.getParameter("password");
		Statement stmt = null;
		boolean found = false;
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUPPLIERID, PASSWORD FROM SUPPLIER;");
            while (rs.next()) {
            	String USER = rs.getString(1);
            	String PASSWORD = rs.getString(2);
            	System.out.println(USER + " " + PASSWORD);
            	if(userid.equals(USER) && password.equals(PASSWORD)) {
                    System.out.println("USER LOGIN SUCCESSFUL");
                    found = true;
                    break;
                }
            }
            stmt.close();
			// System.out.println("DONE");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			if(found) {
				stmt = c.createStatement();
				stmt.execute("DROP TABLE CURRENTSUP;");
                stmt.close();
                stmt = c.createStatement();
                stmt.execute("CREATE TABLE CURRENTSUP (SUPPLIERID TEXT);");
                stmt.close();
                stmt = c.createStatement();
                stmt.execute("INSERT INTO CURRENTSUP (SUPPLIERID) VALUES (\"" + userid + "\");");
                stmt.close();
                stmt = c.createStatement();
                HttpSession session = req.getSession();
                session.setAttribute("userACName", userid);
                
                ResultSet rs1 = stmt.executeQuery("SELECT ITEMNAME, DESCRIPTION, CATEGORY, PRICE FROM ITEMS WHERE SUPPLIERID = "+userid);
                
                String params[][] = new String[4][4];
                int i=0;
                
                while(rs1.next()) {
                	params[i][0] = rs1.getString(1);
                	if (params[i][0] == null || params[i][0].length()==0)
                		params[i][0] = "No Product here";
                	params[i][1] = rs1.getString(2);
                	params[i][2] = rs1.getString(3);
                	params[i][3] = Integer.toString(rs1.getInt(4));
                	i++;
                }
                if(i==3) {
                	params[3][0] = "No Product here";
                }
                
                session.setAttribute("ProductList", params);
                
                res.sendRedirect("supplier_page.jsp");
                
			} else {
				res.sendRedirect("supplier_login.jsp");
			}
		} catch (Exception ae) {
			System.out.println(ae.getMessage());
		}
	}
}