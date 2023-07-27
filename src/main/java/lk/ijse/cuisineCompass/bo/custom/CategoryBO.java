package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.CategoryDTO;
import lk.ijse.cuisineCompass.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CategoryBO extends SuperBO {


    ArrayList<CategoryDTO> getAllCate() throws SQLException, ClassNotFoundException;

    List<String> getAllCateCode() throws SQLException, ClassNotFoundException;
}
