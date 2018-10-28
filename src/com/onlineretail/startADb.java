package com.onlineretail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class startADb {

    String initial;
    startADb() {
        initial = "jdbc:sqlite:D:/Coding Languages/sqlite/db/";
    }
    public void createDatabase(String filename) {
        String url = initial + filename + ".db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if(conn!=null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("db created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String []args) {
        startADb obj = new startADb();
        obj.createDatabase("Xenon");
    }
}
