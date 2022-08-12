package models;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    boolean add(T t) throws SQLException;
    void update(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> readAll() throws SQLException;
}
