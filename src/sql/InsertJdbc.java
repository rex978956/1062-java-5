package sql;

import java.sql.*;

public class InsertJdbc {
    private int id = 0;
    private String userName, mapName;
    private int score;

    //     private boolean isFind = false;
    public InsertJdbc(String mapName, String userName, int score) {
        this.userName = userName;
        this.score = score;
        this.mapName = mapName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        }

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/G05", "root", "0000");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select u.username, score from " + mapName + " u");
            boolean isFind = false;
            while (rs.next()) {
                String sss = rs.getString(1);
                if (sss.matches(userName)) {
                    isFind = true;
                    break;
                }
            }
            if (!isFind) {
                String sql = "INSERT INTO " + mapName + " VALUES ('" + userName + "','" + score + "');";
                //System.out.println(mapName+userName+score);
                stmt.executeUpdate(sql);
            } else {
                new UpdateJdbc(mapName, userName, score);
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
}