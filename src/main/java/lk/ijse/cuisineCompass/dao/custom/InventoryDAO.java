package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InventoryDAO extends CrudDAO<Inventory, String> {
    //place unique methods here

    List<String> getAllInvCodes() throws SQLException, ClassNotFoundException;

}
