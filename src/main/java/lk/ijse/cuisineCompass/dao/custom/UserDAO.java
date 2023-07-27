package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User, String> {

    boolean valid(String userName, String pw) throws SQLException, ClassNotFoundException;

    ResultSet getImage(String userName) throws SQLException, ClassNotFoundException;

    boolean isSup(String userName, String pw) throws SQLException, ClassNotFoundException;
}
