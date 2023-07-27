package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.RecipeDTO;
import lk.ijse.cuisineCompass.dto.RecipeIngredientDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RecipeIngDetailsBO extends SuperBO {

    ArrayList<RecipeIngredientDetailsDTO> getAllIng() throws SQLException, ClassNotFoundException;

    ArrayList<RecipeIngredientDetailsDTO> getAllIngD(String rCode) throws SQLException, ClassNotFoundException;

    boolean save(RecipeIngredientDetailsDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(RecipeIngredientDetailsDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String code) throws SQLException, ClassNotFoundException;

    RecipeIngredientDetailsDTO search(String rCode) throws SQLException, ClassNotFoundException;

}
