package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String USER ="root";
    static final String PASSWD = "";
    static String sql;

    public InitDatabase() {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database…");
                    conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
            stmt = conn.createStatement();

            sql = "CREATE DATABASE TD";
            stmt.executeUpdate(sql);
            System.out.println("Creating database…");
        }catch(SQLException se){
            System.out.println("The database is exist!!");
        }catch(Exception e){
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
            }
        }
        System.out.println("Database Init!");
    }
}