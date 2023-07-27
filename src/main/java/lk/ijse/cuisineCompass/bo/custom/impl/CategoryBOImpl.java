package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.CategoryBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.CategoryDAO;
import lk.ijse.cuisineCompass.entity.Category;
import lk.ijse.cuisineCompass.dto.CategoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {

    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CATEGORY);

    @Override
    public ArrayList<CategoryDTO> getAllCate() throws SQLException, ClassNotFoundException {
        ArrayList<CategoryDTO> allCate= new ArrayList<>();
        ArrayList<Category> all = categoryDAO.getAll();
        for (Category c : all) {
            allCate.add(new CategoryDTO(c.getCode(), c.getType(), c.getDes()));
        }
        return allCate;
    }

    @Override
    public List<String> getAllCateCode() throws SQLException, ClassNotFoundException {
        return categoryDAO.getAllCateCode();
    }
}
