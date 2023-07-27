package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.IngredientDAO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Ingredient;
import lk.ijse.cuisineCompass.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public boolean save(Ingredient entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO ingredient (ingredient_code, description, unit, qty, date) VALUES (?,?,?,?,?)", entity.getIngCode(), entity.getDes(), entity.getUnit(), entity.getQty(), entity.getDate());
        return isSaved;
    }

    @Override
    public boolean update(Ingredient entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("Update ingredient SET description = ?, unit = ?, qty = ?, date = ? WHERE ingredient_code = ?", entity.getDes(), entity.getUnit(), entity.getQty(), entity.getDate(), entity.getIngCode());
        return isUpdated;
    }

    @Override
    public boolean delete(String iCode) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM ingredient WHERE ingredient_code = ?", iCode);
        return isDeleted;
    }

    @Override
    public ArrayList<Ingredient> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Ingredient> allIng = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM ingredient");

        while (rst.next()) {
            Ingredient ingredient = new Ingredient(rst.getString("ingredient_code"), rst.getString("description"), rst.getString("unit"), rst.getDouble("qty"), rst.getString("date"));
            allIng.add(ingredient);
        }
        return allIng;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Ingredient search(String iCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM ingredient WHERE ingredient_code=?", iCode + "");
        rst.next();
        return new Ingredient(iCode + "", rst.getString("description"), rst.getString("unit"),rst.getDouble("qty"), rst.getString("date"));
    }

    @Override
    public ArrayList<Ingredient> getAllIng() throws SQLException, ClassNotFoundException {
        ArrayList<Ingredient> allIng = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT ingredient_code, description FROM ingredient");

        while (rst.next()) {
            Ingredient ingredient = new Ingredient(rst.getString("ingredient_code"), rst.getString("description"));
            allIng.add(ingredient);
        }
        return allIng;
    }

    /*@Override
    public ArrayList<Ingredient> getIngCode() throws SQLException, ClassNotFoundException {
        ArrayList<Ingredient> allCodes = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT ingredient_code FROM ingredient");

        while (rst.next()) {
            //allCodes.add(new String(
               //     resultSet.getString(1)
            //));
            Ingredient ingredient = new Ingredient(rst.getString("ingredient_code"));
            allCodes.add(ingredient);
        }
        return allCodes;
    }*/

    @Override
    public List<String> getIngCode() throws SQLException, ClassNotFoundException {
        /*ArrayList<Ingredient> allIng = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT ingredient_code FROM ingredient");

        while (rst.next()) {
            Ingredient ingredient = new Ingredient(rst.getString("ingredient_code"));
            allIng.add(ingredient);
        }
        return allIng;*/

        List<String> allIng = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT ingredient_code FROM ingredient");

        while (rst.next()) {
            allIng.add(rst.getString("ingredient_code"));
        }
        return allIng;

    }

}
