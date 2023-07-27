package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.entity.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IngredientDAO extends CrudDAO<Ingredient, String> {

    ArrayList<Ingredient> getAllIng() throws SQLException, ClassNotFoundException;

    List<String> getIngCode() throws SQLException, ClassNotFoundException;
}
