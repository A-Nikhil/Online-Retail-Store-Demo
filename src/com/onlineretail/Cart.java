package com.onlineretail;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
			c = DriverManager.getConnection("jdbc:sqlite:D:/Coding Languages/sqlite/db/XenonStore.db");
			HttpSession session = req.getSession();
			
			// Bringing all operations from the cart
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CART.ITEMSID, ITEMS.ITEMNAME, ITEMS.PRICE FROM CART "
            		+ "INNER JOIN ITEMS ON ITEMS.ITEMID = CART.ITEMSID;");
            while(rs.next()) {
            	total += rs.getInt(3);
            }
            stmt.close();
            
            stmt = c.createStatement();
            ResultSet rs2 = stmt.executeQuery("SELECT USERID FROM CURRENT");
            String usernme = rs2.getString(1);
            stmt.close();
            
            stmt = c.createStatement();
            ResultSet rs8 = stmt.executeQuery("SELECT USERID FROM USERS WHERE FNAME = \"" + usernme +"\"");
            String userid = rs8.getString(1);
            stmt.close();
            
            stmt = c.createStatement();
            ResultSet rs3 = stmt.executeQuery("SELECT MAX(ORDERID) FROM ORDERS;");
            int orderid = (rs3.getInt(1) + 1);
            stmt.close();
            
            // Confirming Payment
            String paymentMode = ((String [])req.getParameterValues("PaymentMode"))[0];
            stmt = c.createStatement();
            rs3 = stmt.executeQuery("SELECT MAX(PAYMENTID) FROM PAYMENTS;");
            int paymentid = (rs3.getInt(1) + 1);
            stmt.close();
            
            PreparedStatement paymentForm = c.prepareStatement("INSERT INTO PAYMENTS (PAYMENTID, TOTALCOST, PAYMENTMODE) "
            		+ "VALUES(?, ?, ?);");
            paymentForm.setInt(1, paymentid);
            paymentForm.setInt(2, total);
            paymentForm.setString(3, paymentMode);
            paymentForm.executeUpdate();
            paymentForm.close();
            
            stmt = c.createStatement();
            ResultSet adress = stmt.executeQuery("SELECT ADDRESS, FNAME, MNAME, LNAME FROM USERS WHERE USERID = \""+ userid + "\";");
            String address = adress.getString(1);
            String name = adress.getString(2) + " " + adress.getString(3) + " " + adress.getString(4);
            stmt.close();
            
            stmt = c.createStatement();
            Random rand = new Random();
            int x = rand.nextInt(5) + 1;
            ResultSet shipperid = stmt.executeQuery("SELECT NAME FROM SHIPPER WHERE SHIPPERID = "+x);
            String shipperName = shipperid.getString(1);
            stmt.close();
            
            // Making a final ORDER
            PreparedStatement order = c.prepareStatement("INSERT INTO ORDERS (ORDERID, AMOUNT, ORDERDATE, SHIPPINGADD, USERID, PAYMENTID, SHIPPERID) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
            LocalDate localDate = LocalDate.now();//For reference
            LocalDate del = localDate.plusDays(7);
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    		String date = del.format(formatter);

            order.setInt(1, orderid);
            order.setInt(2, total);
            order.setString(3, date);
            order.setString(4, address);
            order.setString(5, userid);
            order.setInt(6, paymentid);
            order.setInt(7, x);

            order.executeUpdate();
            System.out.print("Executed");
            
            session.setAttribute("totalAmount", total);
            session.setAttribute("shippingadd", address);
            session.setAttribute("PaymentMode", paymentMode);
            session.setAttribute("shipper", shipperName);
            session.setAttribute("name", name);
            session.setAttribute("deldate", date);
            
            res.sendRedirect("final_order.jsp");
            order.close();
            c.close();
            
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}