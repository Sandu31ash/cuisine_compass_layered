package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.CategoryDAO;
import lk.ijse.cuisineCompass.dto.CategoryDTO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.entity.Category;
import lk.ijse.cuisineCompass.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public ArrayList<Category> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Category> allCate = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM category");

        while (rst.next()) {
            Category category = new Category(rst.getString("category_code"), rst.getString("type"), rst.getString("description"));
            allCate.add(category);
        }
        return allCate;
    }

    @Override
    public boolean save(Category dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Category dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Category search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getAllCateCode() throws SQLException, ClassNotFoundException {
        ArrayList<String> allCodes = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT category_code FROM category");

        while (resultSet.next()) {
            allCodes.add(new String(
                    resultSet.getString(1)
            ));
        }
        return allCodes;
    }
}
