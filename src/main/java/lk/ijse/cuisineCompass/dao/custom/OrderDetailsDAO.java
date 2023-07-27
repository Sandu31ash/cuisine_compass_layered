package lk.ijse.cuisineCompass.dao.custom;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails, String> {

    ArrayList<OrderDetails> search2(String oCode) throws SQLException, ClassNotFoundException;

}
