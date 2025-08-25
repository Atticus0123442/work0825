package model;

import java.time.LocalDateTime;

public class food_logs {
	//field

		
		 private int id;
		    private int userId;
		    private int foodId;
		    private int much;
		    private LocalDateTime consumedAt;

		    public food_logs() {
		    	super();
		    }

		    public food_logs(int id, int userId, int foodId, LocalDateTime consumedAt, int much) {
		        this.id = id;
		        this.userId = userId;
		        this.foodId = foodId;
		        this.consumedAt = consumedAt;
		        this.much=much;
		    }

			public int getUserId() {
				return userId;
			}

			public void setUserId(int userId) {
				this.userId = userId;
			}

			public int getFoodId() {
				return foodId;
			}

			public void setFoodId(int foodId) {
				this.foodId = foodId;
			}

			public LocalDateTime getConsumedAt() {
				return consumedAt;
			}

			public void setConsumedAt(LocalDateTime consumedAt) {
				this.consumedAt = consumedAt;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getId() {
				return id;
			}

			public int getMuch() {
				return much;
			}

			public void setMuch(int much) {
				this.much = much;
			}
			
			
		    
}


