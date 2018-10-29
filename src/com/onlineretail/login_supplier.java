package com.onlineretail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.*;

@SuppressWarnings("serial")
class login_supplier extends HttpServlet{
	String userid, password;
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		userid = req.getParameter("userid");
		password = req.getParameter("password");
		Statement stmt = null;
		try {
			stmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db").createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUPPLIERID, PASSWORD FROM SUPPLIER;");
            while (rs.next()) {
            	if(userid.equals(rs.getString(1)) && password.equals(rs.getString("PASSWORD"))) {
                    System.out.println("USER LOGIN SUCCESSFUL");
                    stmt.execute("DROP TABLE CURRENTSUP;");
                    stmt.execute("CREATE TABLE CURRENTSUP (SUPPLIERID TEXT);");
                    stmt.execute("INSERT INTO CURRENTSUP (SUPPLIERID) VALUES (\"" + userid + "\");");
                    HttpSession session = req.getSession();
                    session.setAttribute("supplierACName", userid);
                    res.sendRedirect("supplier_page.html");
                } else {
                    res.sendRedirect("error.html");
                }
            }
			// System.out.println("DONE");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}