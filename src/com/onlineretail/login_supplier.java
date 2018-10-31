package com.onlineretail;

import java.io.IOException;
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
		try {
			stmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db").createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUPPLIERID, PASSWORD FROM SUPPLIER;");
            while (rs.next()) {
            	String USER = rs.getString(1);
            	String PASSWORD = rs.getString(2);
            	System.out.println(USER + " " + PASSWORD);
            	if(userid.equals(USER) && password.equals(PASSWORD)) {
                    System.out.println("USER LOGIN SUCCESSFUL");
                    found = true;
                    stmt.execute("DROP TABLE CURRENTSUP;");
                    stmt.execute("CREATE TABLE CURRENTSUP (SUPPLIERID TEXT);");
                    stmt.execute("INSERT INTO CURRENTSUP (SUPPLIERID) VALUES (\"" + userid + "\");");
                    HttpSession session = req.getSession();
                    session.setAttribute("supplierACName", userid);
                    res.sendRedirect("supplier_page.html");
                }
            }
			// System.out.println("DONE");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			if(found)
				res.sendRedirect("error.html");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}