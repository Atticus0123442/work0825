package dao.impl;
import dao.DbDao;
import model.exercise_logs;
import model.exercises;
import model.foods;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
public class Exercise_logsDaoImpl implements  DbDao<exercise_logs> {
	private static Connection conn=DbConnection.getDb();
	 @Override
	    public void add(exercise_logs exe) {
		 String Sql="insert into exercise_logs(user_id,exercise_id,duration_minutes,exercised_at) values(?,?,?,?)";
			try {
				PreparedStatement ps=conn.prepareStatement(Sql);
				ps.setInt(1, exe.getUserId());
				ps.setInt(2,exe.getExerciseId());
				ps.setInt(3,exe.getMinutes());
				ps.setTimestamp(4, java.sql.Timestamp.valueOf(exe.getExercisedAt()));
				
				
				
				ps.executeUpdate();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    }
	 @Override
	    public exercise_logs getAll() {
		 String sql="select * from exercise_logs";
		 exercise_logs exe=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					exe=new exercise_logs();
					exe.setId(rs.getInt("id"));
					exe.setUserId(rs.getInt("user_id"));
					exe.setExerciseId(rs.getInt("execrise_id"));
					exe.setMinutes(rs.getInt("duration_minutes"));
					exe.setExercisedAt(rs.getTimestamp("exercised_at").toLocalDateTime());
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return exe;
	    }

	    @Override
	    public exercise_logs getById(int id) {
	    	String sql="select * from exercise_logs where id=?";
	    	exercise_logs exe=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					exe=new exercise_logs();
					exe.setId(rs.getInt("id"));
					exe.setUserId(rs.getInt("user_id"));
					exe.setExerciseId(rs.getInt("execrise_id"));
					exe.setMinutes(rs.getInt("duration_minutes"));
					exe.setExercisedAt(rs.getTimestamp("exercised_at").toLocalDateTime());
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return exe;
	    }

	    @Override
	    public void update(exercise_logs exe) {
			String sql="update exercise_logs set duration_minutes=? where id=?";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, exe.getMinutes());
				ps.setInt(2, exe.getId());
				ps.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Override
	    public void delete(exercise_logs exe) {
	    	String sql="delete from exercise_logs where id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, exe.getId());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public Object[][] getAllAsArray(String userid) {
		     String sql = "SELECT * FROM exercise_logs";
		     try (PreparedStatement ps = conn.prepareStatement(sql);
		          ResultSet rs = ps.executeQuery()) {

		         // 先把結果暫存到 List 裡
		         java.util.List<Object[]> rows = new java.util.ArrayList<>();

		         while (rs.next()) {
		        	    Object[] row = new Object[7];

		        	    ExercisesDaoImpl dao = new ExercisesDaoImpl();
		        	    exercises exe = dao.getById(rs.getInt("exercise_id"));
		        	    
		        	    row[0] = exe.getName();
		        	    row[1] = exe.getCalories_burned_per_minute()*rs.getInt("duration_minutes");
		        	    row[2] = rs.getInt("duration_minutes");
		        	    row[3] = rs.getTimestamp("exercised_at").toLocalDateTime();
		        	    row[4] = "更新";
		        	    row[5] = "刪除";
		        	    row[6] = rs.getInt("id");

		        	    rows.add(row);  // 要最後才加進去
		        	}

		         // 轉成二維陣列
		         Object[][] data = new Object[rows.size()][7];
		         for (int i = 0; i < rows.size(); i++) {
		             data[i] = rows.get(i);
		         }

		         return data;

		     } catch (SQLException e) {
		         e.printStackTrace();
		     }

		     return new Object[0][0]; // 失敗回空陣列
		 }
	    
	    public void deletebyid(int id) {
	    	String sql="delete from exercise_logs where id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public double all(int userid) { 
	        LocalDate today = LocalDate.now();
	        LocalDateTime startOfDay = today.atStartOfDay();
	        LocalDateTime startOfNextDay = today.plusDays(1).atStartOfDay();

	        String sql = "SELECT * FROM exercise_logs WHERE user_id=? AND exercised_at >= ? AND exercised_at < ?";

	        double totalCalories = 0;

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, userid);
	            ps.setTimestamp(2, Timestamp.valueOf(startOfDay));
	            ps.setTimestamp(3, Timestamp.valueOf(startOfNextDay));

	            try (ResultSet rs = ps.executeQuery()) {
	                ExercisesDaoImpl dao = new ExercisesDaoImpl(); // ← 移出 while 迴圈，避免重複 new！
	                while (rs.next()) {
	                    int exeId = rs.getInt("exercise_id");
	                    int much = rs.getInt("duration_minutes");

	                    exercises exe = dao.getById(exeId);
	                    if (exe != null) {
	                        totalCalories +=exe.getCalories_burned_per_minute() * much;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return totalCalories;
	    }
	    
	    
}
