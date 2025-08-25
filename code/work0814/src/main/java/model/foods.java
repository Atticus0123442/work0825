package model;

public class foods {
	//field
		private int id;
	    private String name;
	    private double caloriesPerServing;
	    
	    public foods() {
	    	super();
	    }

	    public foods(int id, String name, double caloriesPerServing) {
	        this.id = id;
	        this.name = name;
	        this.caloriesPerServing = caloriesPerServing;
	    }


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public double getCaloriesPerServing() {
			return caloriesPerServing;
		}


		public void setCaloriesPerServing(double caloriesPerServing) {
			this.caloriesPerServing = caloriesPerServing;
		}


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
	    
}
