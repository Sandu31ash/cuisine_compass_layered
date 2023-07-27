package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.dto.RecipeDTO;
import lk.ijse.cuisineCompass.entity.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RecipeDAO extends CrudDAO<Recipe, String> {

    String getLatest() throws SQLException, ClassNotFoundException;

    String getLatestR(String rCode) throws SQLException, ClassNotFoundException;

    ArrayList<Recipe> getAllRecipeApp(String course) throws SQLException, ClassNotFoundException;

    String getRecipeCode(String des) throws SQLException, ClassNotFoundException;

    String getMethod(String rCode) throws SQLException, ClassNotFoundException;

    ResultSet getImage(String rCode) throws SQLException, ClassNotFoundException;
}
