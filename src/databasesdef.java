import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databasesdef {

    String initial;

    // Constructor
    databasesdef() {
        initial = "jdbc:sqlite:D:/Coding Languages/sqlite/db/";
    }

    public void createDatabase(String filename) {
        String url = initial + filename;
        try (Connection conn = DriverManager.getConnection(url)) {
            if(conn!=null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("db created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables(String filename) {
        String url = initial + filename;
        String command[] = new String[12];
        
        command[0] = "CREATE TABLE USERS (" +
                "  USERID TEXT NOT NULL PRIMARY KEY ," +
                "  PASSWORD TEXT NOT NULL," +
                "  FNAME TEXT NOT NULL," +
                "  MNAME TEXT," +
                "  LNAME TEXT NOT NULL ," +
                "  PHNO INTEGER," +
                "  SEX TEXT," +
                "  DOB TEXT" +
                ");";

        command[1] = "CREATE TABLE ITEMS (" +
                "  ITEMID INTEGER NOT NULL PRIMARY KEY," +
                "  ITEMNAME TEXT NOT NULL," +
                "  USEINSTRUCTION TEXT," +
                "  CATEGORY TEXT NOT NULL," +
                "  PRICE REAL" +
                ");";

        command[2] = "CREATE TABLE REVIEWS (" +
                "  REVIEWID INTEGER NOT NULL PRIMARY KEY," +
                "  CONTENT TEXTNOT NOT NULL," +
                "  USERID TEXT NOT NULL," +
                "  ITEMID INTEGER NOT NULL," +
                "  FOREIGN KEY (USERID) REFERENCES USERS(USERID)," +
                "  FOREIGN KEY (ITEMID) REFERENCES ITEMS(ITEMID)" +
                ");";

        command[3] = "CREATE TABLE ADDRESS (" +
                "  ADDRESS TEXT NOT NULL," +
                "  USERID TEXT NOT NULL," +
                "  FOREIGN KEY (USERID) REFERENCES USERS(USERID)" +
                ");";

        command[4] = "CREATE TABLE PAYMENTS (" +
                "  PAYMENTID INTEGER NOT NULL PRIMARY KEY," +
                "  TOTALCOST REAL," +
                "  PAYMENTMODE TEXT" +
                ");";

        command[5] = "CREATE TABLE SHIPPER (" +
                "  SHIPPERID INTEGER NOT NULL PRIMARY KEY," +
                "  NAME TEXT" +
                ");";

        command[6] = "CREATE TABLE SHIPPERCONTACT (" +
                "  SHIPPERID INTEGER NOT NULL," +
                "  CONTACT INTEGER NOT NULL," +
                "  FOREIGN KEY (SHIPPERID) REFERENCES SHIPPER(SHIPPERID)" +
                ");";

        command[7] = "CREATE TABLE SUPPLIER (" +
                "  SUPPLIERID INTEGER NOT NULL PRIMARY KEY," +
                "  NAME TEXT," +
                "  CONTACT REAL" +
                ");";

        command[8] = "CREATE TABLE SUPPLIERCONTACT (" +
                "  SUPPLIERID INTEGER NOT NULL," +
                "  CONTACT INTEGER NOT NULL," +
                "  FOREIGN KEY (SUPPLIERID) REFERENCES SUPPLIER(SUPPLIERID)" +
                ");";

        command[9] = "CREATE TABLE SUPPLIEDBY (" +
                "  SUPPLIERID INTEGER NOT NULL," +
                "  ITEMID INTEGER NOT NULL," +
                "  FOREIGN KEY (SUPPLIERID) REFERENCES SUPPLIER(SUPPLIERID)," +
                "  FOREIGN KEY (ITEMID) REFERENCES ITEMS(ITEMID)" +
                ");";

        command[10] = "CREATE TABLE ORDERS (" +
                "  ORDERID INTEGER NOT NULL PRIMARY KEY," +
                "  AMOUNT REAL," +
                "  SEX TEXT," +
                "  ORDERDATE TEXT," +
                "  SHIPPINGADD TEXT," +
                "  USERID TEXT NOT NULL," +
                "  PAYMENTID TEXT NOT NULL," +
                "  SHIPPERID TEXT NOT NULL," +
                "  FOREIGN KEY (USERID) REFERENCES USERS(USERID)," +
                "  FOREIGN KEY (PAYMENTID) REFERENCES PAYMENTS(PAYMENTID)," +
                "  FOREIGN KEY (SHIPPERID) REFERENCES USERS(USERID)" +
                ");";

        command[11] = "CREATE TABLE REQUIRES(" +
                "  ORDERID INTEGER NOT NULL," +
                "  ITEMID INTEGER NOT NULL," +
                "  FOREIGN KEY (ORDERID) REFERENCES ORDERS(ORDERID)," +
                "  FOREIGN KEY (ITEMID) REFERENCES ITEMS(ITEMID)" +
                ");";

        //try(Connection conn = ) {

        //}
    }

    public static void main(String []args) {
        databasesdef obj = new databasesdef();
        obj.createDatabase("XenonStore");
        //obj.createTables("XenonStore");
    }
}