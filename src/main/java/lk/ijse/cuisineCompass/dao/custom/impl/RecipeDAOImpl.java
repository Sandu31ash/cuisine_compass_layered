package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.RecipeDAO;
import lk.ijse.cuisineCompass.dto.RecipeDTO;
import lk.ijse.cuisineCompass.entity.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDAOImpl implements RecipeDAO {
    @Override
    public boolean save(Recipe entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO recipe (recipe_code, description, category_code, method, course, user_name) VALUES (?,?,?,?,?,?)", entity.getRecipeCode(), entity.getDes(), entity.getCateCode(), entity.getMethod(), entity.getCourse(), entity.getAddedBy());
        return isSaved;
    }

    @Override
    public boolean update(Recipe entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE recipe SET description = ?, category_code = ?, method = ?, course = ?, user_name = ? WHERE recipe_code = ?", entity.getDes(), entity.getCateCode(), entity.getMethod(), entity.getCourse(), entity.getAddedBy(), entity.getRecipeCode());
        return isUpdated;
    }

    @Override
    public boolean delete(String rCode) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM recipe WHERE recipe_code = ?", rCode);
        return isDeleted;
    }

    @Override
    public ArrayList<Recipe> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Recipe> allRecipes = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM recipe");

        while (rst.next()) {
            Recipe recipe = new Recipe(rst.getString("recipe_code"), rst.getString("description"), rst.getString("category_code"), rst.getString("method"), rst.getString("course"), rst.getString("user_name"));
            allRecipes.add(recipe);
        }
        return allRecipes;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Recipe search(String rCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM recipe WHERE recipe_code=?", rCode + "");
        rst.next();
        return new Recipe(rCode + "", rst.getString("description"), rst.getString("category_code"),rst.getString("method"), rst.getString("course"), rst.getString("user_name"));
    }

    @Override
    public String getLatest() throws SQLException, ClassNotFoundException {
        String rCode = SQLUtil.execute("SELECT recipe_code FROM recipe ORDER BY recipe_code DESC LIMIT 1");
        return rCode;
    }

    @Override
    public String getLatestR(String rCode) throws SQLException, ClassNotFoundException {
        String des = SQLUtil.execute("SELECT description FROM recipe WHERE recipe_code = ?", rCode);
        return des;
    }

    @Override
    public ArrayList<Recipe> getAllRecipeApp(String course) throws SQLException, ClassNotFoundException {
        ArrayList<Recipe> allRecipe = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT description, course FROM recipe");

        while (rst.next()) {
            if(rst.getString("course").equalsIgnoreCase(course)) {
                Recipe recipe = new Recipe(rst.getString("description"));
                allRecipe.add(recipe);
            }
        }
        return allRecipe;
    }

    @Override
    public String getRecipeCode(String des) throws SQLException, ClassNotFoundException {
        String rCode = SQLUtil.execute("SELECT recipe_code FROM recipe WHERE description LIKE ?", des);
        return rCode;
    }

    @Override
    public String getMethod(String rCode) throws SQLException, ClassNotFoundException {
        String method = SQLUtil.execute("SELECT method FROM recipe WHERE description LIKE ?", rCode);
        return method;
    }

    @Override
    public ResultSet getImage(String rCode) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT image FROM image_recipe WHERE id = ?", rCode);

        if(rst.next()){
            return rst;
        }
        return null;
    }
}
