package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.SupplierDTO;
import lk.ijse.cuisineCompass.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {

    boolean save(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    List<String> getAllSupId() throws SQLException, ClassNotFoundException;

    SupplierDTO search(String sId) throws SQLException, ClassNotFoundException;
}
