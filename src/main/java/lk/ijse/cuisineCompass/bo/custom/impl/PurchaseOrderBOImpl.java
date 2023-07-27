package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.PurchaseOrderBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.OrderDAO;
import lk.ijse.cuisineCompass.dao.custom.OrderDetailsDAO;
import lk.ijse.cuisineCompass.dto.InventoryDTO;
import lk.ijse.cuisineCompass.dto.OrderDTO;
import lk.ijse.cuisineCompass.dto.OrderDetailsDTO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Inventory;
import lk.ijse.cuisineCompass.entity.Order;
import lk.ijse.cuisineCompass.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);


    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> allOrders= new ArrayList<>();
        ArrayList<Order> all = orderDAO.getAll();
        for (Order o : all) {
            allOrders.add(new OrderDTO(o.getCode(), o.getId(), o.getDate(), o.getOrderBy()));
        }
        return allOrders;
    }

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> allOrderDetails= new ArrayList<>();
        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();
        for (OrderDetails oD : all) {
            allOrderDetails.add(new OrderDetailsDTO(oD.getCode(), oD.getiCode(), oD.getPrice(), oD.getQty() , oD.getTot()));
        }
        return allOrderDetails;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(dto.getCode(), dto.getId(), dto.getDate(), dto.getOrderBy()));
    }

    @Override
    public boolean save1(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(new OrderDetails(dto.getCode(), dto.getiCode(),dto.getPrice(), dto.getQty(), dto.getTot()));
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(dto.getCode(), dto.getId(), dto.getDate(), dto.getOrderBy()));
    }

    @Override
    public boolean update1(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.update(new OrderDetails(dto.getCode(), dto.getiCode(),dto.getPrice(), dto.getQty(), dto.getTot()));
    }

    @Override
    public boolean delete(String oCode) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(oCode);
    }

    @Override
    public boolean delete1(String oCode) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.delete(oCode);
    }

    @Override
    public OrderDTO search(String oCode) throws SQLException, ClassNotFoundException {
        Order o = orderDAO.search(oCode);
        OrderDTO orderDTO = new OrderDTO(o.getCode(), o.getId(), o.getDate(), o.getOrderBy());
        return orderDTO;
    }

    @Override
    public OrderDetailsDTO search1(String oCode) throws SQLException, ClassNotFoundException {
        OrderDetails o = orderDetailsDAO.search(oCode);
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(o.getCode(), o.getiCode(), o.getPrice(), o.getQty(), o.getTot());
        return orderDetailsDTO;
    }

    //@Override
    public ArrayList<OrderDetailsDTO> search2(String oCode) throws SQLException, ClassNotFoundException {
        /*ArrayList<OrderDetails> o = orderDetailsDAO.search1(oCode);
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(o.getCode(), o.getiCode(), o.getPrice(), o.getQty(), o.getTot());
        return orderDetailsDTO;*/

        ArrayList<OrderDetailsDTO> allOrderDetails= new ArrayList<>();
        ArrayList<OrderDetails> all = orderDetailsDAO.search2(oCode);
        for (OrderDetails oD : all) {
            allOrderDetails.add(new OrderDetailsDTO( oD.getiCode(), oD.getPrice(), oD.getQty() , oD.getTot()));
        }
        return allOrderDetails;
    }

    @Override
    public List<String> getAllOCodes() throws SQLException, ClassNotFoundException {
        return orderDAO.getAllOCodes();
    }

}
