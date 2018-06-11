package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class InitTable {

    public InitTable(){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TD", "root", "");
            System.out.println("connected to the database...");

            stmt = conn.createStatement();
            System.out.println("Inserting records");

            stmt.executeUpdate("CREATE TABLE Hard_Rock " + "(username VARCHAR(15), score INT)");
            stmt.executeUpdate("CREATE TABLE MURICA " + "(username VARCHAR(15), score INT)");
            stmt.executeUpdate("CREATE TABLE Worm " + "(username VARCHAR(15), score INT)");
            System.out.println("Tables are init!");
            stmt.close();
            conn.close();
            System.out.println("Disconnected from database");
        }catch(SQLException se){
            System.out.println("The Tables are exist!!");
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
        System.out.println("Table Init!");
    }

}