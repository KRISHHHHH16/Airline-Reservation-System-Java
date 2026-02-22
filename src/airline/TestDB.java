package airline;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline_db", "root", "191230");
            System.out.println("✅ Database Connected Successfully!");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
        }
    }
}

