package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.InventoryDAO;
import lk.ijse.cuisineCompass.entity.Ingredient;
import lk.ijse.cuisineCompass.entity.Inventory;
import lk.ijse.cuisineCompass.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
    @Override
    public boolean save(Inventory entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO inventory (inventory_code, description, unit, PAR_level, qtyOnHand, date) VALUES (?,?,?,?,?,?)", entity.getIngCode(), entity.getDes(), entity.getUnit(), entity.getPar(), entity.getQty(), entity.getDate());
        return isSaved;
    }

    @Override
    public boolean update(Inventory entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE inventory SET description = ?, unit = ?, PAR_level = ?, qtyOnHand = ?, date = ? WHERE inventory_code = ?", entity.getDes(), entity.getUnit(), entity.getPar(), entity.getQty(), entity.getDate(), entity.getIngCode());
        return isUpdated;
    }

    @Override
    public boolean delete(String iCode) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM inventory WHERE inventory_code = ?", iCode);
        return isDeleted;
    }

    @Override
    public ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> allInv = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM inventory");

        while (rst.next()) {
            Inventory inventory = new Inventory(rst.getString("inventory_code"), rst.getString("description"), rst.getString("unit"), rst.getDouble("PAR_level"), rst.getDouble("qtyOnHand"), rst.getString("date"));
            allInv.add(inventory);
        }
        return allInv;
    }

    @Override
    public boolean exist(String iCode) throws SQLException, ClassNotFoundException {
        boolean isExist = SQLUtil.execute("SELECT inventory_code FROM inventory WHERE inventory_code = ?", iCode);
        return isExist;
    }

    @Override
    public Inventory search(String iCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM inventory WHERE inventory_code = ?", iCode + "");
        rst.next();
        return new Inventory(iCode + "", rst.getString("description"), rst.getString("unit"),rst.getDouble("PAR_level"), rst.getDouble("qtyOnHand"), rst.getString("date"));
    }

    @Override
    public List<String> getAllInvCodes() throws SQLException, ClassNotFoundException {
        /*ArrayList<Inventory> allInv = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT inventory_code FROM inventory");

        while (rst.next()) {
            Inventory inventory = new Inventory(rst.getString("inventory_code"));
            allInv.add(inventory);
        }
        return allInv;*/

        List<String> allInv = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT inventory_code FROM inventory");

        while (rst.next()) {
            allInv.add(rst.getString("inventory_code"));
        }
        return allInv;

    }

    /*@Override
    public Inventory search(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> allInv = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?", id + "");
        while (rst.next()) {
            Inventory inventory = new Inventory(rst.getString("inventory_code"), rst.getString("description"), rst.getString("unit"), rst.getDouble("PAR_level"), rst.getDouble("qtyOnHand"), rst.getString("date"));
            allInv.add(inventory);
        }
        return allInv;
    }*/

}
