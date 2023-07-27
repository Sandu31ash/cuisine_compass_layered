package lk.ijse.cuisineCompass.bo.custom;

import lk.ijse.cuisineCompass.bo.SuperBO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.OrderDTO;
import lk.ijse.cuisineCompass.dto.OrderDetailsDTO;
import lk.ijse.cuisineCompass.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;

    boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean save1(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean update1(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String oCode) throws SQLException, ClassNotFoundException;

    boolean delete1(String oCode) throws SQLException, ClassNotFoundException;

    OrderDTO search(String oCode) throws SQLException, ClassNotFoundException;

    OrderDetailsDTO search1(String oCode) throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetailsDTO> search2(String oCode) throws SQLException, ClassNotFoundException;

    List<String> getAllOCodes() throws SQLException, ClassNotFoundException;

    //boolean save1(OrderDTO dto) throws SQLException, ClassNotFoundException;
}
