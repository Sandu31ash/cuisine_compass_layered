package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.SupplierBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.SupplierDAO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.SupplierDTO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean save(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getId(), dto.getName(), dto.getEmail(), dto.getContact()));
    }

    @Override
    public boolean update(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(), dto.getName(), dto.getEmail(), dto.getContact()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSuppliers= new ArrayList<>();
        ArrayList<Supplier> all = supplierDAO.getAll();
        for (Supplier s : all) {
            allSuppliers.add(new SupplierDTO(s.getId(),s.getName(),s.getContact(),s.getEmail()));
        }
        return allSuppliers;
    }

    @Override
    public List<String> getAllSupId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllSupId();
    }

    @Override
    public SupplierDTO search(String sId) throws SQLException, ClassNotFoundException {
        Supplier s = supplierDAO.search(sId);
        SupplierDTO supplierDTO = new SupplierDTO(s.getId(), s.getName(), s.getEmail(), s.getContact());
        return supplierDTO;
    }
}
