package model;


public class exercises {
	//field

	    private int id;
	    private String name;
	    private int calories_burned_per_minute;


	    public exercises() {
	    	super();
	    }

	    public exercises(int id, String name, int calories_burned_per_minute) {
	        this.id = id;
	        this.name = name;
	        this.calories_burned_per_minute = calories_burned_per_minute;

	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCalories_burned_per_minute() {
			return calories_burned_per_minute;
		}

		public void setCalories_burned_per_minute(int calories_burned_per_minute) {
			this.calories_burned_per_minute = calories_burned_per_minute;
		}


	    
}
