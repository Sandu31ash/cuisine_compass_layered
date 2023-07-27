package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO extends CrudDAO<Category, String> {

    List<String> getAllCateCode() throws SQLException, ClassNotFoundException;

}
