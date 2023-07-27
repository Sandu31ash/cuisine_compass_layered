package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.IngredientBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.IngredientDAO;
import lk.ijse.cuisineCompass.entity.Ingredient;
import lk.ijse.cuisineCompass.dto.IngredientDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientBOImpl implements IngredientBO {

    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INGREDIENT);

    @Override
    public boolean save(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        return ingredientDAO.save(new Ingredient(dto.getIngCode(),dto.getDes(),dto.getUnit(),dto.getQty(), dto.getDate()));
    }

    @Override
    public boolean update(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        return ingredientDAO.update(new Ingredient(dto.getIngCode(),dto.getDes(),dto.getUnit(),dto.getQty(), dto.getDate()));
    }

    @Override
    public boolean delete(String iCode) throws SQLException, ClassNotFoundException {
        return ingredientDAO.delete(iCode);
    }

    @Override
    public IngredientDTO search(String iCode) throws SQLException, ClassNotFoundException {
        Ingredient i = ingredientDAO.search(iCode);
        IngredientDTO ingredientDTO = new IngredientDTO(i.getIngCode(), i.getDes(), i.getUnit(), i.getQty(), i.getDate());
        return ingredientDTO;
    }

    @Override
    public ArrayList<IngredientDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<IngredientDTO> allIng= new ArrayList<>();
        ArrayList<Ingredient> all = ingredientDAO.getAll();
        for (Ingredient i : all) {
            allIng.add(new IngredientDTO(i.getIngCode(),i.getDes(),i.getUnit(),i.getQty(),i.getDate()));
        }
        return allIng;
    }

    /*@Override
    public ArrayList<String> getIngCode() throws SQLException, ClassNotFoundException {
        ArrayList<String> allIng= new ArrayList<>();
        ArrayList<Ingredient> all = ingredientDAO.getIngCode();
        for (Ingredient i : all) {
            allIng.add(String.valueOf(new IngredientDTO(i.getIngCode())));
        }
        return allIng;
    }*/

    @Override
    public List<String> getIngCode() throws SQLException, ClassNotFoundException {
        return ingredientDAO.getIngCode();
    }

    @Override
    public ArrayList<IngredientDTO> getAllIng() throws SQLException, ClassNotFoundException {
        ArrayList<IngredientDTO> allIng= new ArrayList<>();
        ArrayList<Ingredient> all = ingredientDAO.getAll();
        for (Ingredient i : all) {
            allIng.add(new IngredientDTO(i.getIngCode(), i.getDes(), i.getUnit(), i.getQty(), i.getDate()));
        }
        return allIng;
    }
}
