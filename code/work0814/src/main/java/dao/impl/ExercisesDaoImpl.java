
package dao.impl;
import dao.DbDao;
import model.exercises;
import model.foods;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
public class ExercisesDaoImpl implements  DbDao<exercises> {
	private static Connection conn=DbConnection.getDb();
	 @Override
	    public void add(exercises exe) {
		 String Sql="insert into exercises(name,calories_burned_per_minute) values(?,?)";
			try {
				PreparedStatement ps=conn.prepareStatement(Sql);
				ps.setString(1,exe.getName());
				ps.setInt(2,exe.getCalories_burned_per_minute());
				
				
				
				ps.executeUpdate();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 @Override
	    public exercises getAll() {
		 String sql="select * from exercises";
		 exercises exe=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					exe=new exercises();
					exe.setId(rs.getInt("id"));
					exe.setName(rs.getString("name"));
					exe.setCalories_burned_per_minute(rs.getInt("calories_burned_per_minute"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return exe;
	    }

	    @Override
	    public exercises getById(int id) {
	    	String sql="select * from exercises where id=?";
	    	exercises exe=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					exe=new exercises();
					exe.setId(rs.getInt("id"));
					exe.setName(rs.getString("name"));
					exe.setCalories_burned_per_minute(rs.getInt("calories_burned_per_minute"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return exe;
	    }

	    @Override
	    public void update(exercises exe) {
			String sql="update exercises set name=?,calories_burned_per_minute=? where id=?";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, exe.getName());
				ps.setInt(2, exe.getCalories_burned_per_minute());
				ps.setInt(3, exe.getId());
				
				ps.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Override
	    public void delete(exercises exe) {
	    	String sql="delete from exercises where id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, exe.getId());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public exercises[] searchByName(String keyword) {
	    	exercises[] result = new exercises[100]; // 預設最多 100 筆
	        int index = 0;

	        String sql = "SELECT * FROM exercises WHERE name LIKE ?";
	        try (Connection conn = DbConnection.getDb(); 
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, "%" + keyword + "%");
	            ResultSet rs = ps.executeQuery();
	            while (rs.next() && index < result.length) {
	            	exercises exe = new exercises();
	                exe.setId(rs.getInt("id"));
	                exe.setName(rs.getString("name"));
	                exe.setCalories_burned_per_minute(rs.getInt("calories_burned_per_minute"));
	                result[index++] = exe;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // 裁剪為實際長度
	        exercises[] actual = new exercises[index];
	        System.arraycopy(result, 0, actual, 0, index);
	        return actual;
	    }
	    
	    
	    public exercises searchbyname(String name) {
	     	String sql="select * from exercises where name=?";
	     	exercises exe=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, name);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					exe=new exercises();
					exe.setId(rs.getInt("id"));
	                exe.setName(rs.getString("name"));
	                exe.setCalories_burned_per_minute(rs.getInt("calories_burned_per_minute"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "新增失敗");
			}
			
			
			return exe;
	    }
}
