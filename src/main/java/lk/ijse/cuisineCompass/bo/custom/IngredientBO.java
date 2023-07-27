package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.IngredientDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IngredientBO extends SuperBO {

    boolean save(IngredientDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(IngredientDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String iCode) throws SQLException, ClassNotFoundException;

    IngredientDTO search(String iCode) throws SQLException, ClassNotFoundException;

    ArrayList<IngredientDTO> getAll() throws SQLException, ClassNotFoundException;

    List<String> getIngCode() throws SQLException, ClassNotFoundException;

    ArrayList<IngredientDTO> getAllIng() throws SQLException, ClassNotFoundException;
}
