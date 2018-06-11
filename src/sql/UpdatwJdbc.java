package sql;

import java.sql.*;

public class UpdatwJdbc {

    public UpdatwJdbc(String mapName, String userName, int score) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        }

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TD", "root", "");

            stmt = conn.createStatement();
            PreparedStatement sql = conn.prepareStatement("UPDATE " + mapName + " SET username = ?, score = ? WHERE username = ? ;");
            sql.setString(1, userName);
            sql.setInt(2, score);
            sql.setString(3, userName);
            sql.executeUpdate();

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

//    select s.student_id, student_name, java_teacher , teacher_name from student_table s , teacher_table t where t.teacher_id = s.java_teacher;