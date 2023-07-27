package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.InventoryBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.InventoryDAO;
import lk.ijse.cuisineCompass.dto.IngredientDTO;
import lk.ijse.cuisineCompass.entity.Ingredient;
import lk.ijse.cuisineCompass.entity.Inventory;
import lk.ijse.cuisineCompass.dto.InventoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryBOImpl implements InventoryBO {

    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);

    @Override
    public boolean save(InventoryDTO dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(dto.getIngCode(), dto.getDes(), dto.getUnit(), dto.getPar(), dto.getQty(), dto.getDate()));
    }

    @Override
    public boolean update(InventoryDTO dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(dto.getIngCode(), dto.getDes(), dto.getUnit(), dto.getPar(), dto.getQty(), dto.getDate()));
    }

    @Override
    public boolean delete(String iCode) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(iCode);
    }

    @Override
    public boolean exist(String iCode) throws SQLException, ClassNotFoundException {
        return inventoryDAO.exist(iCode);
    }

    @Override
    public InventoryDTO search(String iCode) throws SQLException, ClassNotFoundException {
        Inventory i = inventoryDAO.search(iCode);
        InventoryDTO inventoryDTO = new InventoryDTO(i.getIngCode(), i.getDes(), i.getUnit(), i.getPar(), i.getQty(), i.getDate());
        return inventoryDTO;
    }

    @Override
    public ArrayList<InventoryDTO> getAllInv() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryDTO> allInv= new ArrayList<>();
        ArrayList<Inventory> all = inventoryDAO.getAll();
        for (Inventory i : all) {
            allInv.add(new InventoryDTO(i.getIngCode(),i.getDes(),i.getUnit(),i.getPar(),i.getQty(),i.getDate()));
        }
        return allInv;
    }

    /*@Override
    public ArrayList<String> getAllInvCodes() throws SQLException, ClassNotFoundException {
        ArrayList<String> allInv= new ArrayList<>();
        ArrayList<Inventory> all = inventoryDAO.getAllInvCodes();
        for (Inventory i : all) {
            allInv.add(String.valueOf(new InventoryDTO(i.getIngCode())));
        }
        return allInv;
    }*/

    @Override
    public List<String> getAllInvCodes() throws SQLException, ClassNotFoundException {
        return inventoryDAO.getAllInvCodes();
    }

}
