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
			// handle the error
		}

		Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TD", "root", "");

			stmt= conn.createStatement();

//			ResultSet rs = stmt.executeQuery("select s.student_id, student_name, java_teacher , teacher_name" + " from student_table s , teacher_table t"
//					+ " where t.teacher_id = s.java_teacher");
			ResultSet rs = stmt.executeQuery("select map.username, map.score from "+mapName+" map"+
					" ORDER BY score DESC");
			while (rs.next()||username.size()>10) {
//			    System.out.println(rs.getString(1)+"   "+rs.getInt(2));
				        username.add(rs.getString(1));
						score.add(rs.getInt(2));
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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