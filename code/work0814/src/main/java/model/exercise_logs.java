package model;

import java.time.LocalDateTime;

public class exercise_logs {
	//field
	 private int id;
	    private int userId;
	    private int exerciseId;
	    private int minutes;
	    private LocalDateTime exercisedAt;

	    public exercise_logs() {}

	    public exercise_logs(int id, int userId, int exerciseId, int minutes, LocalDateTime exercisedAt) {
	        this.id = id;
	        this.userId = userId;
	        this.exerciseId = exerciseId;
	        this.minutes = minutes;
	        this.exercisedAt = exercisedAt;
	    }

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getExerciseId() {
			return exerciseId;
		}

		public void setExerciseId(int exerciseId) {
			this.exerciseId = exerciseId;
		}

		public int getMinutes() {
			return minutes;
		}

		public void setMinutes(int minutes) {
			this.minutes = minutes;
		}

		public LocalDateTime getExercisedAt() {
			return exercisedAt;
		}

		public void setExercisedAt(LocalDateTime exercisedAt) {
			this.exercisedAt = exercisedAt;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
		
		
}
