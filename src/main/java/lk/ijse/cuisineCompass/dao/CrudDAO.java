package lk.ijse.cuisineCompass.dao;

import lk.ijse.cuisineCompass.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T, id> extends SuperDAO{

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;

}
