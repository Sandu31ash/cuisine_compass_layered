package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {

    boolean save(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean valid(String userName, String pw) throws SQLException, ClassNotFoundException;

    ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException;

    UserDTO search(String id) throws SQLException, ClassNotFoundException;

    ResultSet getImage(String userName) throws SQLException, ClassNotFoundException;

    boolean isSup(String userName, String pw) throws SQLException, ClassNotFoundException;
}
