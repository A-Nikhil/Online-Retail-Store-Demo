package com.onlineretail;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signup {
    public static void main(String []args) {
        // Insert input here
        String userid = "", password = "", fname = "", mname = "", lname = "", sex = "", dob = "";
        long phno = 0L;
        PreparedStatement pstmt = null;
        try {
            pstmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db")
                    .prepareStatement("INSERT INTO USERS(USERID, PASSWORD, FNAME, MNAME, LNAME, PHNO, SEX, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, userid);
            pstmt.setString(2, password);
            pstmt.setString(3, fname);
            pstmt.setString(4, mname);
            pstmt.setString(5, lname);
            pstmt.setLong(6, phno);
            pstmt.setString(7, sex);
            pstmt.setString(8, dob);

            //Execute it
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
