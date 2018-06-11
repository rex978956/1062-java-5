package sql;

import java.sql.*;
import java.util.ArrayList;

public class ReadJdbc {
    private ArrayList<String> username = new ArrayList<String>(10);
    private ArrayList<Integer> score = new ArrayList<Integer>(10);

    public ReadJdbc(String mapName) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        }

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/G05", "root", "0000");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select map.username, map.score from " + mapName + " map" +
                    " ORDER BY score DESC");
            while (rs.next() || username.size() > 10) {
                username.add(rs.getString(1));
                score.add(rs.getInt(2));
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
            }
        }

    }

    public ArrayList<String> getUsername() {
        return username;
    }

    public void setUsername(ArrayList<String> username) {
        this.username = username;
    }

    public ArrayList<Integer> getScore() {
        return score;
    }

    public void setScore(ArrayList<Integer> score) {
        this.score = score;
    }
}