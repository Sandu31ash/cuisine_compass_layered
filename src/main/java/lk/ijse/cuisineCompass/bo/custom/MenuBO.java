package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.InventoryDTO;
import lk.ijse.cuisineCompass.dto.MenuDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MenuBO extends SuperBO {

    ArrayList<MenuDTO> getAllMenu() throws SQLException, ClassNotFoundException;

    ResultSet getImage(String mCode) throws SQLException, ClassNotFoundException;

    boolean save(MenuDTO menuDTO) throws SQLException, ClassNotFoundException;

    boolean update(MenuDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String mCode) throws SQLException, ClassNotFoundException;

    MenuDTO search(String mCode) throws SQLException, ClassNotFoundException;

    ArrayList<MenuDTO> getAllApp( String course, String meal) throws SQLException, ClassNotFoundException;
}
