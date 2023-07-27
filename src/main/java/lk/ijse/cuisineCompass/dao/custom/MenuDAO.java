package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuDAO extends CrudDAO<Menu, String> {
    //place unique methods here

    ResultSet getImageMenu(String mCode) throws SQLException, ClassNotFoundException;

    ArrayList<Menu> getAllApp(String course, String meal) throws SQLException, ClassNotFoundException;

}
