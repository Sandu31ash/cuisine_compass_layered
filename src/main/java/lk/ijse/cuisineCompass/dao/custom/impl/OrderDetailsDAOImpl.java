package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.OrderDetailsDAO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Order;
import lk.ijse.cuisineCompass.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO order_details (order_code, inventory_code, unit_price, qty, tot) VALUES (?,?,?,?,?)", entity.getCode(), entity.getiCode(), entity.getPrice(), entity.getQty(), entity.getTot());
        return isSaved;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE order_details SET unit_price = ?, qty = ?, tot = ? WHERE order_code = ? AND inventory_code = ?", entity.getPrice(), entity.getQty(), entity.getTot(), entity.getCode(), entity.getiCode());
        return isUpdated;
    }

    @Override
    public boolean delete(String oCode) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM order_details WHERE order_code = ?", oCode);
        return isDeleted;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> allOrderDetails = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM order_details");

        while (rst.next()) {
            OrderDetails orderDetails = new OrderDetails(rst.getString("order_code"), rst.getString("inventory_code"), rst.getDouble("unit_price"), rst.getDouble("qty"), rst.getDouble("tot"));
            allOrderDetails.add(orderDetails);
        }
        return allOrderDetails;
    }

    @Override
    public boolean exist(String oCode) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String oCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM order_details WHERE order_code=?", oCode + "");
        rst.next();
        return new OrderDetails(oCode + "", rst.getString("inventory_code"), rst.getDouble("unit_price"),rst.getDouble("qty"), rst.getDouble("tot"));
    }

    @Override
    public ArrayList<OrderDetails> search2(String oCode) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = SQLUtil.execute("SELECT * FROM order_details WHERE order_code=?", oCode + "");
        rst.next();
        return new OrderDetails(oCode + "", rst.getString("inventory_code"), rst.getDouble("unit_price"),rst.getDouble("qty"), rst.getDouble("tot"));
*/
        ArrayList<OrderDetails> allOrderDetails = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM order_details WHERE order_code=?", oCode + "");

        while (rst.next()) {
            OrderDetails orderDetails = new OrderDetails( rst.getString("inventory_code"), rst.getDouble("unit_price"),rst.getDouble("qty"), rst.getDouble("tot"));
            allOrderDetails.add(orderDetails);
        }
        return allOrderDetails;
    }
}
