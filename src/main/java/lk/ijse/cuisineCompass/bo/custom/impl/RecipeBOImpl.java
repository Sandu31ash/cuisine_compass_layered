package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.RecipeBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.RecipeDAO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.RecipeDTO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeBOImpl implements RecipeBO {

    RecipeDAO recipeDAO = (RecipeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECIPE);

    @Override
    public ArrayList<RecipeDTO> getAllRecipe() throws SQLException, ClassNotFoundException {
        ArrayList<RecipeDTO> allRecipes= new ArrayList<>();
        ArrayList<Recipe> all = recipeDAO.getAll();
        for (Recipe r : all) {
            allRecipes.add(new RecipeDTO(r.getRecipeCode(),r.getDes(),r.getCateCode(),r.getMethod(),r.getCourse(), r.getAddedBy()));
        }
        return allRecipes;
    }

    @Override
    public boolean save(RecipeDTO dto) throws SQLException, ClassNotFoundException {
        return recipeDAO.save(new Recipe(dto.getRecipeCode(), dto.getDes(), dto.getCateCode(), dto.getMethod(), dto.getCourse(), dto.getAddedBy()));
    }

    @Override
    public boolean update(RecipeDTO dto) throws SQLException, ClassNotFoundException {
        return recipeDAO.update(new Recipe(dto.getRecipeCode(), dto.getDes(), dto.getCateCode(), dto.getMethod(), dto.getCourse(), dto.getAddedBy()));
    }

    @Override
    public boolean delete(String rCode) throws SQLException, ClassNotFoundException {
        return recipeDAO.delete(rCode);
    }

    @Override
    public RecipeDTO search(String rCode) throws SQLException, ClassNotFoundException {
        Recipe r = recipeDAO.search(rCode);
        RecipeDTO recipeDTO = new RecipeDTO(r.getRecipeCode(), r.getDes(), r.getCateCode(), r.getMethod(), r.getCourse(), r.getAddedBy());
        return recipeDTO;
    }

    @Override
    public String getLatest() throws SQLException, ClassNotFoundException {
        return recipeDAO.getLatest();
    }

    @Override
    public String getLatestR(String rCode) throws SQLException, ClassNotFoundException {
        return recipeDAO.getLatestR(rCode);
    }

    @Override
    public ArrayList<RecipeDTO> getAllRecipeApp(String course) throws SQLException, ClassNotFoundException {
        ArrayList<RecipeDTO> allRecipes= new ArrayList<>();
        ArrayList<Recipe> all = recipeDAO.getAllRecipeApp(course);
        for (Recipe r : all) {
            allRecipes.add(new RecipeDTO(r.getDes()));
        }
        return allRecipes;
        //return recipeDAO.getAllRecipeApp();
    }

    @Override
    public String getRecipeCode(String des) throws SQLException, ClassNotFoundException {
        return recipeDAO.getRecipeCode(des);
    }

    @Override
    public String getMethod(String rCode) throws SQLException, ClassNotFoundException {
        return recipeDAO.getMethod(rCode);
    }

    @Override
    public ResultSet getImage(String rCode) throws SQLException, ClassNotFoundException {
        return  recipeDAO.getImage(rCode);
    }
}
