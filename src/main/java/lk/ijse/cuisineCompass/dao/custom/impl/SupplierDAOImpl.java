package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.CrudDAO;
import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.SupplierDAO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Inventory;
import lk.ijse.cuisineCompass.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO supplier (supplier_id, name, contact, email) VALUES(?,?,?,?)",entity.getId(), entity.getName(), entity.getContact(), entity.getEmail());
        return isSaved;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE supplier SET name = ?, contact = ?, email = ? WHERE supplier_id = ?", entity.getName(), entity.getContact(), entity.getEmail(), entity.getId());
        return isUpdated;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM supplier WHERE supplier_id = ?",id);
        return isDeleted;
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSuppliers = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");

        while (rst.next()) {
            Supplier supplier = new Supplier(rst.getString("supplier_id"), rst.getString("name"), rst.getString("contact"), rst.getString("email"));
            allSuppliers.add(supplier);
        }
        return allSuppliers;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE supplier_id=?", id + "");
        rst.next();
        return new Supplier(id + "", rst.getString("name"), rst.getString("email"), rst.getString("contact"));
    }

    @Override
    public List<String> getAllSupId() throws SQLException, ClassNotFoundException {
        List<String> allSuppliers = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT supplier_id FROM supplier");

        while (rst.next()) {
            //Supplier supplier = new Supplier(rst.getString("supplier_id"));
            allSuppliers.add(rst.getString("supplier_id"));
        }
        return allSuppliers;
    }
}
