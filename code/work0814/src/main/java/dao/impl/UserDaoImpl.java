package dao.impl;
import dao.DbDao;
import model.users;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDaoImpl implements  DbDao<users> {
	private static Connection conn=DbConnection.getDb();
	 @Override
	    public void add(users user) {
		 String Sql="insert into users(name,age,gender,weight,height,created_at,username,password,classes,location) values(?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement ps=conn.prepareStatement(Sql);
				ps.setString(1, user.getName());
				ps.setInt(2, user.getAge());
				ps.setString(3, user.getGender());
				ps.setDouble(4, user.getWeight());
				ps.setDouble(5, user.getHeight());
				ps.setTimestamp(6, java.sql.Timestamp.valueOf(user.getCreatAt()));
				ps.setString(7, user.getUsername());
				ps.setString(8, user.getPassword());
				ps.setString(9, user.getClasses());
				ps.setString(10, user.getLocation());
				
				
				
				ps.executeUpdate();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 @Override
	    public users getAll() {
		 String sql="select * from users";
			users user=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					user=new users();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setAge(rs.getInt("age"));
					user.setGender(rs.getString("gender"));
					user.setWeight(rs.getDouble("weight"));
					user.setHeight(rs.getDouble("height"));
					user.setCreatAt(rs.getTimestamp("created_at").toLocalDateTime());
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setClasses(rs.getString("classes"));
					user.setLocation(rs.getString("location"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return user;
	    }

	    @Override
	    public users getById(int id) {
	    	String sql="select * from users where id=?";
			users user=null;
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					user=new users();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setAge(rs.getInt("age"));
					user.setGender(rs.getString("gender"));
					user.setWeight(rs.getDouble("weight"));
					user.setHeight(rs.getDouble("height"));
					user.setCreatAt(rs.getTimestamp("created_at").toLocalDateTime());
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setClasses(rs.getString("classes"));
					user.setLocation(rs.getString("location"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (user == null) {
		        user = new users(); // 回傳一個空物件，避免 NPE
		    }

			return user;
	    }

	    @Override
	    public void update(users user) {
			String sql="UPDATE users SET name=?, age=?, gender=?, weight=?, height=?, username=?, password=?,classes=?,location=? WHERE id=?";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setInt(2, user.getAge());
				ps.setString(3, user.getGender());
				ps.setDouble(4, user.getWeight());
				ps.setDouble(5, user.getHeight());
				ps.setString(6, user.getUsername());
				ps.setString(7, user.getPassword());
				ps.setString(8, user.getClasses());
				ps.setString(9, user.getLocation());
				ps.setInt(10, user.getId());
				ps.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Override
	    public void delete(users user) {
	    	String sql="delete from users where id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, user.getId());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public users login(String username, String password) {
	        users user = null;
	        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
	        try  {
	        		PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, username);
	            ps.setString(2, password);

	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                user = new users();
	                user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setAge(rs.getInt("age"));
					user.setGender(rs.getString("gender"));
					user.setWeight(rs.getDouble("weight"));
					user.setHeight(rs.getDouble("height"));
					user.setCreatAt(rs.getTimestamp("creat_at").toLocalDateTime());
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
	                
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }
	    public boolean login(String username) {
	        String sql = "SELECT id FROM users WHERE username = ?";
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, username);
	            ResultSet rs = ps.executeQuery();
	            return rs.next(); // 有資料就代表登入成功
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
