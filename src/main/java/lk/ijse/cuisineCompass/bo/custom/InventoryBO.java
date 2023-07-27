package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.IngredientDTO;
import lk.ijse.cuisineCompass.dto.InventoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InventoryBO extends SuperBO {

    boolean save(InventoryDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(InventoryDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String iCode) throws SQLException, ClassNotFoundException;

    boolean exist(String iCode) throws SQLException, ClassNotFoundException;

    InventoryDTO search(String iCode) throws SQLException, ClassNotFoundException;

    ArrayList<InventoryDTO> getAllInv() throws SQLException, ClassNotFoundException;

    List<String> getAllInvCodes() throws SQLException, ClassNotFoundException;

    //boolean save(String iCode, String des, String unit, double par, double qty, Date date);
}
