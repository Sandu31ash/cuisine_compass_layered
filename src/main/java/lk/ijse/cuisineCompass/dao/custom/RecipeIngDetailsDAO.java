package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.dto.RecipeIngredientDetailsDTO;
import lk.ijse.cuisineCompass.entity.RecipeIngredientDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RecipeIngDetailsDAO extends CrudDAO<RecipeIngredientDetails, String> {

    ArrayList<RecipeIngredientDetails> getAllIngD(String rCode) throws SQLException, ClassNotFoundException;
}
