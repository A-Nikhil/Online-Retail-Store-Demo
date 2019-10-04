package com.onlineretail;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class signup extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        PreparedStatement pstmt = null;
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String fname = req.getParameter("fname");
        String mname= req.getParameter("mname");
        if(mname == null)
        	mname = "";
        String lname = req.getParameter("lname");
        String sex = req.getParameter("sex");
        Long phno = Long.parseLong(req.getParameter("phno"));
        String location = req.getParameter("location");
        try {
            // It is better to initialize a db url before
            String dbURL = "jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db";
        	Connection c = DriverManager.getConnection(dbURL);
            pstmt = c.prepareStatement("INSERT INTO USERS(USERID, PASSWORD, FNAME, MNAME, LNAME, PHNO, SEX, ADDRESS) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, userid);
            pstmt.setString(2, password);
            pstmt.setString(3, fname);
            pstmt.setString(4, mname);
            pstmt.setString(5, lname);
            pstmt.setLong(6, phno);
            pstmt.setString(7, sex);
            pstmt.setString(8, location);

            //Execute it
            pstmt.executeUpdate();
            pstmt.close();
            c.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
