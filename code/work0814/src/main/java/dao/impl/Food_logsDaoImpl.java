
package dao.impl;
import dao.DbDao;
import model.food_logs;
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
public class Food_logsDaoImpl implements  DbDao<food_logs> {
	private static Connection conn=DbConnection.getDb();
	 @Override
	    public void add(food_logs food) {
		 String Sql="insert into food_logs(user_id,food_id,consumed_at,much) values(?,?,?,?)";
			try {
				PreparedStatement ps=conn.prepareStatement(Sql);
				ps.setInt(1,food.getUserId());
				ps.setInt(2, food.getFoodId());
				ps.setTimestamp(3, java.sql.Timestamp.valueOf(food.getConsumedAt()));
				ps.setInt(4,food.getMuch());
				
				ps.executeUpdate();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 @Override
	    public food_logs getAll() {
		 String sql="select * from food_logs";
		 food_logs food=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					food=new food_logs();
					food.setId(rs.getInt("id"));
					food.setUserId(rs.getInt("user_id"));
					food.setFoodId(rs.getInt("food_id"));
					food.setConsumedAt(rs.getTimestamp("consumed_at").toLocalDateTime());
					food.setMuch(rs.getInt("much"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return food;
	    }
	 
	 public Object[][] getAllAsArray(String userid) {
	     String sql = "SELECT * FROM food_logs";
	     try (PreparedStatement ps = conn.prepareStatement(sql);
	          ResultSet rs = ps.executeQuery()) {

	         // 先把結果暫存到 List 裡
	         java.util.List<Object[]> rows = new java.util.ArrayList<>();

	         while (rs.next()) {
	        	    Object[] row = new Object[7];

	        	    FoodsDaoImpl dao = new FoodsDaoImpl();
	        	    foods food = dao.getById(rs.getInt("food_id"));
	        	    
	        	    row[0] = food.getName();
	        	    row[1] = food.getCaloriesPerServing()*rs.getInt("much");
	        	    row[2] = rs.getInt("much");
	        	    row[3] = rs.getTimestamp("consumed_at").toLocalDateTime();
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


	    @Override
	    public food_logs getById(int id) {
	    	String sql="select * from food_logs where id=?";
	    	food_logs food=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					food = new food_logs();  // 這裡要 new 一個物件
					food.setId(rs.getInt("id"));
					food.setUserId(rs.getInt("user_id"));
					food.setFoodId(rs.getInt("food_id"));
					food.setConsumedAt(rs.getTimestamp("consumed_at").toLocalDateTime());
					food.setMuch(rs.getInt("much"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				JOptionPane.showMessageDialog(null, "找不到要更新的紀錄8989！");
				e.printStackTrace();
			}
			
			
			return food;
	    }

	    @Override
	    public void update(food_logs food) {
			String sql="update food_logs set much=? where id=?";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, food.getMuch());
				ps.setInt(2, food.getId());
				
				ps.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Override
	    public void delete(food_logs food) {
	    	String sql="delete from food_logs where id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, food.getId());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public void deletebyid(int id) {
	    	String sql="delete from food_logs where id=?";
			
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

	        String sql = "SELECT food_id, much FROM food_logs WHERE user_id=? AND consumed_at >= ? AND consumed_at < ?";

	        double totalCalories = 0;

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, userid);
	            ps.setTimestamp(2, Timestamp.valueOf(startOfDay));
	            ps.setTimestamp(3, Timestamp.valueOf(startOfNextDay));

	            try (ResultSet rs = ps.executeQuery()) {
	                FoodsDaoImpl dao = new FoodsDaoImpl(); // ← 移出 while 迴圈，避免重複 new！
	                while (rs.next()) {
	                    int foodId = rs.getInt("food_id");
	                    int much = rs.getInt("much");

	                    foods food = dao.getById(foodId);
	                    if (food != null) {
	                        totalCalories += food.getCaloriesPerServing() * much;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return totalCalories;
	    }


}

