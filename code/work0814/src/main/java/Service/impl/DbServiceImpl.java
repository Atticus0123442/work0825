package Service.impl;
import dao.impl.Exercise_logsDaoImpl;
import dao.impl.ExercisesDaoImpl;
import dao.impl.Food_logsDaoImpl;
import dao.impl.FoodsDaoImpl;
import dao.impl.UserDaoImpl;
import model.exercise_logs;
import model.food_logs;
import model.foods;
import model.users;
import model.exercises;
import Service.DbService;

public class DbServiceImpl implements DbService{

	public static void main(String[] args) {
//		Member member=new Member();
//		member.setId(9);
//		
//		
//		System.out.println(new DbServiceImpl().deleteMember(member));
		
		

	}
	
	private static Exercise_logsDaoImpl exelogs =new Exercise_logsDaoImpl();//運動紀錄
	private static ExercisesDaoImpl exe =new ExercisesDaoImpl();//運動
	private static Food_logsDaoImpl foodlogs =new Food_logsDaoImpl();//食物紀錄
	private static FoodsDaoImpl foods =new FoodsDaoImpl();//食物
	private static UserDaoImpl user =new UserDaoImpl();//食物

	//新增
	@Override
	public void addMember(exercise_logs v) {
		exelogs.add(v);
	}
	@Override
	public void addMember(exercises v) {
		exe.add(v);
	}
	@Override
	public void addMember(food_logs v) {
		foodlogs.add(v);
	}
	@Override
	public void addMember(foods v) {
		foods.add(v);
	}
	@Override
	public void addMember(users v) {
		user.add(v);
	}
	
	//取得全部

	@Override
	public exercise_logs getAllExerciseLogs() {
		// TODO Auto-generated method stub
		return exelogs.getAll();
	}
	@Override
	public exercises getAllExercises() {
		// TODO Auto-generated method stub
		return exe.getAll();
	}
	@Override
	public food_logs getAllFoodLogs() {
		// TODO Auto-generated method stub
		return foodlogs.getAll();
	}
	@Override
	public foods getAllFoods() {
		// TODO Auto-generated method stub
		return foods.getAll();
	}
	@Override
	public users getAllUsers() {
		// TODO Auto-generated method stub
		return user.getAll();
	}
	
	

	@Override
	public boolean updateExerciseLog(exercise_logs v) {
		/*
		 * 1.檢查Id-->null
		 * 2.null-->false
		 * 3.!=null-->調閱-->改-->true
		 */		
		boolean update=false;
		exercise_logs m=exelogs.getById(v.getId());
		if(m!=null)
		{
			update=true;
			m.setExerciseId(v.getExerciseId());
			m.setMinutes(v.getMinutes());
			m.setId(v.getId());
			
			exelogs.update(m);
		}
		
		
		return update;
	}
	@Override
	public boolean updateExercise(exercises v) {
		/*
		 * 1.檢查Id-->null
		 * 2.null-->false
		 * 3.!=null-->調閱-->改-->true
		 */		
		boolean update=false;
		exercises m=exe.getById(v.getId());
		if(m!=null)
		{
			update=true;
			m.setName(v.getName());
			m.setCalories_burned_per_minute(v.getCalories_burned_per_minute());
			m.setId(v.getId());
			
			exe.update(m);
		}
		
		
		return update;
	}
	@Override
	public boolean updateFoodLog(food_logs v) {
		/*
		 * 1.檢查Id-->null
		 * 2.null-->false
		 * 3.!=null-->調閱-->改-->true
		 */		
		boolean update=false;
		food_logs m=foodlogs.getById(v.getId());
		if(m!=null)
		{
			update=true;
			m.setFoodId(v.getFoodId());
			m.setConsumedAt(v.getConsumedAt());
			m.setId(v.getId());
			
			foodlogs.update(m);
		}
		
		
		return update;
	}
	@Override
	public boolean updateFood(foods v) {
		/*
		 * 1.檢查Id-->null
		 * 2.null-->false
		 * 3.!=null-->調閱-->改-->true
		 */		
		boolean update=false;
		foods m=foods.getById(v.getId());
		if(m!=null)
		{
			update=true;
			m.setName(v.getName());
			m.setCaloriesPerServing(v.getCaloriesPerServing());
			m.setId(v.getId());
			
			
			foods.update(m);
		}
		
		
		return update;
	}
	@Override
	public boolean updateUser(users v) {
		/*
		 * 1.檢查Id-->null
		 * 2.null-->false
		 * 3.!=null-->調閱-->改-->true
		 */		
		boolean update=false;
		users m=user.getById(v.getId());
		if(m!=null)
		{
			update=true;
			m.setUsername(v.getUsername());
			m.setPassword(v.getPassword());
			m.setId(v.getId());
			
			user.update(m);
		}
		
		
		return update;
	}
	


	@Override
	public boolean deleteExerciseLog(exercise_logs v) {
		/*
		 * 1.檢查 member-->id
		 * 2.null-->false
		 * 3.!=null--->true-->刪除
		 */
		
		boolean delete=false;
		exercise_logs m=exelogs.getById(v.getId());
		if(m!=null)
		{
			delete=true;
			
			exelogs.delete(m);
		}		
		
		
		return delete;
	}
	@Override
	public boolean deleteExercise(exercises v) {
		/*
		 * 1.檢查 member-->id
		 * 2.null-->false
		 * 3.!=null--->true-->刪除
		 */
		
		boolean delete=false;
		exercises m=exe.getById(v.getId());
		if(m!=null)
		{
			delete=true;
			
			exe.delete(m);
		}		
		
		
		return delete;
	}
	@Override
	public boolean deleteFoodLog(food_logs v) {
		/*
		 * 1.檢查 member-->id
		 * 2.null-->false
		 * 3.!=null--->true-->刪除
		 */
		
		boolean delete=false;
		food_logs m=foodlogs.getById(v.getId());
		if(m!=null)
		{
			delete=true;
			
			foodlogs.delete(m);
		}		
		
		
		return delete;
	}
	@Override
	public boolean deleteFood(foods v) {
		/*
		 * 1.檢查 member-->id
		 * 2.null-->false
		 * 3.!=null--->true-->刪除
		 */
		
		boolean delete=false;
		foods m=foods.getById(v.getId());
		if(m!=null)
		{
			delete=true;
			
			foods.delete(m);
		}		
		
		
		return delete;
	}
	@Override
	public boolean deleteUser(users v) {
		/*
		 * 1.檢查 member-->id
		 * 2.null-->false
		 * 3.!=null--->true-->刪除
		 */
		
		boolean delete=false;
		users m=user.getById(v.getId());
		if(m!=null)
		{
			delete=true;
			
			user.delete(m);
		}		
		
		
		return delete;
	}


}