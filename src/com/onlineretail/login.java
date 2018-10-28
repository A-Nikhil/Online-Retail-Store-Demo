package com.onlineretail;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class login extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) {
        int userid = Integer.parseInt(req.getParameter("username"));
        int password = Integer.parseInt(req.getParameter("password"));
        System.out.println(userid + " "+ password);
    }
    /*
    public static void main(String []args) {
        String userid = "";
        String password = "";
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT USERID, PASSWORD FROM USERS;");
            while (rs.next()) {
                if(userid.equals(rs.getString("USERID")) && password.equals(rs.getString("PASSWORD"))) {
                    System.out.println("USER LOGIN SUCCESSFUL");
                    // Proceed further
                } else {
                    System.out.println("USER LOGIN UNSUCCESSFUL");
                    // Same goes again
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
}
