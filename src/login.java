import java.sql.*;

public class login {

    public void login(String userid, String password) {
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
    }
    public static void main(String []args) throws SQLException {
        login obj = new login();
        String userid = "";
        String password = "";
        obj.login(userid, password);
    }
}
