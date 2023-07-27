package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.entity.Inventory;
import lk.ijse.cuisineCompass.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier, String> {

    List<String> getAllSupId() throws SQLException, ClassNotFoundException;

}
