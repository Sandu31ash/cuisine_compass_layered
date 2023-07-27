package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.RecipeIngDetailsBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.RecipeIngDetailsDAO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.RecipeDTO;
import lk.ijse.cuisineCompass.dto.RecipeIngredientDetailsDTO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Recipe;
import lk.ijse.cuisineCompass.entity.RecipeIngredientDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeIngDetailsBOImpl implements RecipeIngDetailsBO {

    RecipeIngDetailsDAO recipeIngDetailsDAO = (RecipeIngDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECIPE_ING_DETAILS);

    @Override
    public ArrayList<RecipeIngredientDetailsDTO> getAllIng() throws SQLException, ClassNotFoundException {
        ArrayList<RecipeIngredientDetailsDTO> allIng= new ArrayList<>();
        ArrayList<RecipeIngredientDetails> all = recipeIngDetailsDAO.getAll();
        for (RecipeIngredientDetails r : all) {
            allIng.add(new RecipeIngredientDetailsDTO(r.getRecipeCode(), r.getIngCode(), r.getUnit(), r.getQty()));
        }
        return allIng;
    }

    @Override
    public boolean save(RecipeIngredientDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return recipeIngDetailsDAO.save(new RecipeIngredientDetails(dto.getRecipeCode(), dto.getIngCode(), dto.getUnit(), dto.getQty()));
    }

    @Override
    public boolean update(RecipeIngredientDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return recipeIngDetailsDAO.update(new RecipeIngredientDetails(dto.getRecipeCode(), dto.getIngCode(), dto.getUnit(), dto.getQty()));
    }

    @Override
    public boolean delete(String rCode) throws SQLException, ClassNotFoundException {
        return recipeIngDetailsDAO.delete(rCode);
    }

    @Override
    public RecipeIngredientDetailsDTO search(String rCode) throws SQLException, ClassNotFoundException {
        RecipeIngredientDetails r = recipeIngDetailsDAO.search(rCode);
        RecipeIngredientDetailsDTO recipeIngDTO = new RecipeIngredientDetailsDTO(r.getRecipeCode(), r.getIngCode(), r.getUnit(), r.getQty());
        return recipeIngDTO;
    }

    @Override
    public ArrayList<RecipeIngredientDetailsDTO> getAllIngD(String rCode) throws SQLException, ClassNotFoundException {
        ArrayList<RecipeIngredientDetailsDTO> allIng= new ArrayList<>();
        ArrayList<RecipeIngredientDetails> all = recipeIngDetailsDAO.getAllIngD(rCode);
        for (RecipeIngredientDetails r : all) {
            allIng.add(new RecipeIngredientDetailsDTO(r.getRecipeCode(), r.getIngCode(), r.getUnit(), r.getQty()));
        }
        return allIng;
    }

}
