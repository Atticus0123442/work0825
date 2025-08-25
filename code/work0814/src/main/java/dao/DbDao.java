package dao;


public interface DbDao<T> {
    void add(T entity);
    T getById(int id);
    T getAll();
    void update(T entity);
    void delete(T entity);
    
}