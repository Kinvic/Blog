package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    String url = "jdbc:postgresql://localhost:5432/blog";
    private static Connection conn;
    public Connection connectDB() {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if(conn == null){
            try {
                conn = DriverManager.getConnection(url, "postgres", "postgres");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
