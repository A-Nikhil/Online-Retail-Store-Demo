package com.onlineretail;

import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class supplier_add extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		PreparedStatement pstmt = null;
        String itemname = req.getParameter("userid");
        String useInstruction = req.getParameter("password");
        String category = req.getParameter("fname");
        int price = Integer.parseInt(req.getParameter("phno"));
        try {
            pstmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db")
                    .prepareStatement("INSERT INTO ITEMS (ITEMID, ITEMNAME, USEINSTRUCTION, CATEGORY, PRICE, SUPPLIERID) VALUES (?, ?, ?, ?, ?, ?);");
            Statement stmt = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db").createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUPPID FROM CURRENTSUP"); 
            int suppid = rs.getInt(1);
            pstmt.setString(1, itemname);
            pstmt.setString(2, useInstruction);
            pstmt.setString(3, category);
            pstmt.setInt(4, price);
            pstmt.setInt(5, suppid);

            //Execute it
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
}
