package com.onlineretail;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.*;

public class signup extends HttpServlet {
	String userid, password, fname, mname, lname, sex, dob;
    long phno;
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
    	// Operation
    }
    
    public static void main(String []args) {
    	signup obj = new signup();
        PreparedStatement pstmt = null;
        try {
            pstmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db")
                    .prepareStatement("INSERT INTO USERS(USERID, PASSWORD, FNAME, MNAME, LNAME, PHNO, SEX, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, obj.userid);
            pstmt.setString(2, obj.password);
            pstmt.setString(3, obj.fname);
            pstmt.setString(4, obj.mname);
            pstmt.setString(5, obj.lname);
            pstmt.setLong(6, obj.phno);
            pstmt.setString(7, obj.sex);
            pstmt.setString(8, obj.dob);

            //Execute it
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
