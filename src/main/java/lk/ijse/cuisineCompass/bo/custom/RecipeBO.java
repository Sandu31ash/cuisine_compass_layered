package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.RecipeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RecipeBO extends SuperBO {

    ArrayList<RecipeDTO> getAllRecipe() throws SQLException, ClassNotFoundException;

    boolean save(RecipeDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(RecipeDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String rCode) throws SQLException, ClassNotFoundException;

    RecipeDTO search(String rCode) throws SQLException, ClassNotFoundException;

    String getLatest() throws SQLException, ClassNotFoundException;

    String getLatestR(String rCode) throws SQLException, ClassNotFoundException;

    ArrayList<RecipeDTO> getAllRecipeApp(String course) throws SQLException, ClassNotFoundException;

    String getRecipeCode(String des) throws SQLException, ClassNotFoundException;

    String getMethod(String rCode) throws SQLException, ClassNotFoundException;

    ResultSet getImage(String rCode) throws SQLException, ClassNotFoundException;
}
