package com.onlineretail;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class Cart extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        int total = 0;
		Connection c; Statement stmt;
		try {
			// Creating a HTTPSESSION
			@SuppressWarnings("unused")
			HttpSession session = req.getSession();
			
			// Bringing all operations from the cart
			c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CART.ITEMSID, ITEMS.ITEMNAME, ITEMS.PRICE FROM CART "
            		+ "INNER JOIN ITEMS ON ITEMS.ITEMID = CART.ITEMSID;");
            while(rs.next()) {
            	total += rs.getInt(3);
            }
            stmt.close();
            c.close();
            
            stmt = c.createStatement();
            ResultSet rs2 = stmt.executeQuery("SELECT USERID FROM CURRENT");
            String userid = rs2.getString(1);
            stmt.close();
            c.close();
            
            stmt = c.createStatement();
            ResultSet rs3 = stmt.executeQuery("SELECT MAX(ORDERID) FROM ORDERS;");
            int orderid = (rs3.getInt(1) + 1);
            stmt.close();
            c.close();
            
            // Confirming Payment
            String paymentMode = req.getParameter("Payment Mode");
            stmt = c.createStatement();
            rs3 = stmt.executeQuery("SELECT MAX(PAYMENTID) FROM PAYMENT;");
            int paymentid = (rs3.getInt(1) + 1);
            stmt.close();
            c.close();
            
            PreparedStatement paymentForm = c.prepareStatement("INSERT INTO PAYMENTS (PAYMENTID, TOTALCOST, PAYMENTMODE) "
            		+ "VALUES(?, ?, ?);");
            paymentForm.setInt(1, paymentid);
            paymentForm.setInt(2, total);
            paymentForm.setString(3, paymentMode);
            paymentForm.executeUpdate();
            paymentForm.close();
            c.close();
            
            stmt = c.createStatement();
            ResultSet address = stmt.executeQuery("SELECT ADDRESS FROM USERS WHERE USERID = \""+ userid + "\"");
            stmt.close();
            c.close();
            
            stmt = c.createStatement();
            ResultSet shipperid = stmt.executeQuery("SELECT SHIPPERID FROM SHIPPER");
            stmt.close();
            c.close();
            
            // Making a final ORDER
            PreparedStatement order = c.prepareStatement("INSERT INTO ORDERS (ORDERID, AMOUNT, ORDERDATE, SHIPPINGADD, USERID, PAYMENTID, SHIPPERID) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
            LocalDate localDate = LocalDate.now();//For reference
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    		String date = localDate.format(formatter);

            order.setInt(1, orderid);
            order.setInt(2, total);
            order.setString(3, date);
            order.setString(4, address.getString(1));
            order.setString(5, userid);
            order.setInt(6, paymentid);
            order.setInt(7, shipperid.getInt(1));

            order.executeUpdate();
            System.out.print("Executed");
            
          /*session.setAttribute("totalAmount", total);
            session.setAttribute("totalAmount", total); */
            
            order.close();
            c.close();
            
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}