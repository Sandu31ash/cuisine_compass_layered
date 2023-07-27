package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.dao.SuperDAO;
import lk.ijse.cuisineCompass.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order, String> {

    List<String> getAllOCodes() throws SQLException, ClassNotFoundException;
}
