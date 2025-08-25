package model;

import java.time.LocalDateTime;

public class users {
	//field
	private int id;
    private String name;
    private int age;
    private String gender; // 建議使用 ENUM 類型
    private double weight;
    private double height;
    private LocalDateTime creatAt;
    private String username;
    private String password;
    private String classes;
    private String Location;
		public users() {
			super();
			// TODO Auto-generated constructor stub
		}
		public users(int id, String name, int age, String gender, double weight, double height, LocalDateTime creatAt,String username,String password,String classes,String Location) {
	        this.id = id;
	        this.name = name;
	        this.age = age;
	        this.gender = gender;
	        this.weight = weight;
	        this.height = height;
	        this.creatAt=creatAt;
	        this.password=password;
	        this.username=username;
	        this.classes=classes;
	        this.Location=Location;
	        
	    }
		public String getLocation() {
			return Location;
		}
		public void setLocation(String location) {
			Location = location;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public double getWeight() {
			return weight;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height;
		}
		public int getId() {
			return id;
		}
		public LocalDateTime getCreatAt() {
			return creatAt;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setCreatAt(LocalDateTime creatAt) {
			this.creatAt = creatAt;
		}
		public String getClasses() {
			return classes;
		}
		public void setClasses(String classes) {
			this.classes = classes;
		}
		
		
		
}
