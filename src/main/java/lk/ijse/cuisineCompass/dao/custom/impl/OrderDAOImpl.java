package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.OrderDAO;
import lk.ijse.cuisineCompass.entity.Ingredient;
import lk.ijse.cuisineCompass.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO ordr (order_code, supplier_id, date, user_name) VALUES (?,?,?,?,?)", entity.getCode(), entity.getId(), entity.getDate(), entity.getOrderBy());
        return isSaved;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE ordr SET supplier_id = ?, date = ?, user_name = ? WHERE order_code = ? ", entity.getId(), entity.getDate(), entity.getOrderBy(), entity.getCode());
        return isUpdated;
    }

    @Override
    public boolean delete(String oCode) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM ordr WHERE order_code = ?", oCode);
        return isDeleted;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrders = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM ordr");

        while (rst.next()) {
            Order order = new Order(rst.getString("order_code"), rst.getString("supplier_id"), rst.getString("date"), rst.getString("user_name"));
            allOrders.add(order);
        }
        return allOrders;
    }

    @Override
    public boolean exist(String oCode) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String oCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM ordr WHERE order_code=?", oCode + "");
        rst.next();
        return new Order(oCode + "", rst.getString("supplier_id"), rst.getString("date"),rst.getString("user_name") );
    }

    @Override
    public List<String> getAllOCodes() throws SQLException, ClassNotFoundException {
        List<String> allOCodes = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT order_code FROM ordr");

        while (rst.next()) {
            allOCodes.add(rst.getString("order_code"));
        }
        return allOCodes;
    }
}
