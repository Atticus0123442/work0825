
package dao.impl;
import dao.DbDao;
import model.foods;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FoodsDaoImpl implements  DbDao<foods> {
	private static Connection conn=DbConnection.getDb();
	 @Override
	    public void add(foods food) {
		 String Sql="insert into foods(name,calories_per_serving) values(?,?)";
			try {
				PreparedStatement ps=conn.prepareStatement(Sql);
				ps.setString(1,food.getName());
				ps.setDouble(2,food.getCaloriesPerServing());

				ps.executeUpdate();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 @Override
	    public foods getAll() {
		 String sql="select * from foods";
		 foods food=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					food=new foods();
					food.setId(rs.getInt("id"));
					food.setName(rs.getString(rs.getString("name")));
					food.setCaloriesPerServing(rs.getDouble("calories_per_serving"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return food;
	    }

	    @Override
	    public foods getById(int id) { 
	        String sql = "SELECT * FROM foods WHERE id = ?";
	        foods food = null;

	        try {
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                food = new foods();
	                food.setId(rs.getInt("id"));
	                food.setName(rs.getString("name")); // ✅ 正確寫法
	                food.setCaloriesPerServing(rs.getDouble("calories_per_serving"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }


	        return food;
	    }


	    @Override
	    public void update(foods food) {
			String sql="update foods set name=?,calories_per_serving=? where id=?";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,food.getName());
				ps.setDouble(2, food.getCaloriesPerServing());
				ps.setInt(3, food.getId());
				
				ps.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Override
	    public void delete(foods food) {
	    	String sql="delete from foods where id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, food.getId());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public foods[] searchByName(String keyword) {
	        foods[] result = new foods[100]; // 預設最多 100 筆
	        int index = 0;

	        String sql = "SELECT * FROM foods WHERE name LIKE ?";
	        try (Connection conn = DbConnection.getDb(); 
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, "%" + keyword + "%");
	            ResultSet rs = ps.executeQuery();
	            while (rs.next() && index < result.length) {
	                foods food = new foods();
	                food.setId(rs.getInt("id"));
	                food.setName(rs.getString("name"));
	                food.setCaloriesPerServing(rs.getDouble("calories_per_serving"));
	                result[index++] = food;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // 裁剪為實際長度
	        foods[] actual = new foods[index];
	        System.arraycopy(result, 0, actual, 0, index);
	        return actual;
	    }
	    
	    
	    public foods searchbyname(String name) {
	     	String sql="select * from foods where name=?";
	    	foods food=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, name);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					food=new foods();
					food.setId(rs.getInt("id"));
					food.setName(rs.getString(rs.getString("name")));
					food.setCaloriesPerServing(rs.getDouble("calories_per_serving"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return food;
	    }
	    


}
