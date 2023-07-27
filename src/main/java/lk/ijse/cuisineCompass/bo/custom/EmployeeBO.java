package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO search(String id) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
}
