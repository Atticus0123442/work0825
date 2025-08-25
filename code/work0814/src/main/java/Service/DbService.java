package Service;

import model.exercise_logs;
import model.exercises;
import model.food_logs;
import model.foods;
import model.users;



public interface DbService {
//	//create
//	void addMember(T entity);
//	
//	
//	//read
//	T findAll();
//	
//	
//	
//	//update
//	boolean updateMember(T entity);
//	
//	
//	
//	//delete
//	
//	boolean deleteMember(T entity);

	//create

	void addMember(exercise_logs v);


	void addMember(exercises v);


	void addMember(food_logs v);


	void addMember(foods v);


	void addMember(users v);
	


	// Read
    exercise_logs getAllExerciseLogs();
    exercises getAllExercises();
    food_logs getAllFoodLogs();
    foods getAllFoods();
    users getAllUsers();
    
    //update
    boolean updateExerciseLog(exercise_logs v);
    boolean updateExercise(exercises v);
    boolean updateFoodLog(food_logs v);
    boolean updateFood(foods v);
    boolean updateUser(users v);
    
    //delete
    boolean deleteExerciseLog(exercise_logs v);
    boolean deleteExercise(exercises v);
    boolean deleteFoodLog(food_logs v);
    boolean deleteFood(foods v);
    boolean deleteUser(users v);

}