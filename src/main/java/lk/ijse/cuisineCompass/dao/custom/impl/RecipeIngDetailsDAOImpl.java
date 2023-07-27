package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.RecipeIngDetailsDAO;
import lk.ijse.cuisineCompass.dto.RecipeIngredientDetailsDTO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Recipe;
import lk.ijse.cuisineCompass.entity.RecipeIngredientDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeIngDetailsDAOImpl implements RecipeIngDetailsDAO {
    @Override
    public boolean save(RecipeIngredientDetails entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO recipe_ingredient_details (recipe_code, ingredient_code, unit, qty) VALUES (?,?,?,?)", entity.getRecipeCode(), entity.getIngCode(), entity.getUnit(), entity.getQty());
        return isSaved;
    }

    @Override
    public boolean update(RecipeIngredientDetails entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE recipe_ingredient_details SET unit = ?, qty = ? WHERE recipe_code = ? AND ingredient_code = ?", entity.getUnit(), entity.getQty(), entity.getRecipeCode() ,entity.getIngCode());
        return isUpdated;
    }

    @Override
    public boolean delete(String rCode) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM recipe_ingredient_details WHERE recipe_code = ?", rCode);
        return isDeleted;
    }

    @Override
    public ArrayList<RecipeIngredientDetails> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<RecipeIngredientDetails> allIngD = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM recipe_ingredient_details");

        while (rst.next()) {
            RecipeIngredientDetails recipeIngD = new RecipeIngredientDetails(rst.getString("recipe_code"), rst.getString("ingredient_code"), rst.getString("unit"), rst.getDouble("qty"));
            allIngD.add(recipeIngD);
        }
        return allIngD;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RecipeIngredientDetails search(String rCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM recipe_ingredient_details WHERE recipe_code=?", rCode + "");
        rst.next();
        return new RecipeIngredientDetails(rCode + "", rst.getString("ingredient_code"), rst.getString("unit"),rst.getDouble("qty"));
    }

    @Override
    public ArrayList<RecipeIngredientDetails> getAllIngD(String rCode) throws SQLException, ClassNotFoundException {
        ArrayList<RecipeIngredientDetails> allIngD = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT ingredient_code, unit, qty FROM recipe_ingredient_details WHERE recipe_code= ?", rCode);

        while (rst.next()) {
            RecipeIngredientDetails recipeIngD = new RecipeIngredientDetails(rst.getString("ingredient_code"), rst.getString("unit"), rst.getDouble("qty"));
            allIngD.add(recipeIngD);
        }
        return allIngD;
    }
}
