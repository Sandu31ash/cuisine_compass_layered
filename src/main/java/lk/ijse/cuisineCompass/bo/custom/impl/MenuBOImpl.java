package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.MenuBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.MenuDAO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.InventoryDTO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Inventory;
import lk.ijse.cuisineCompass.entity.Menu;
import lk.ijse.cuisineCompass.dto.MenuDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuBOImpl implements MenuBO {

    MenuDAO menuDAO = (MenuDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MENU);

    @Override
    public ArrayList<MenuDTO> getAllMenu() throws SQLException, ClassNotFoundException {
        ArrayList<MenuDTO> allMenu = new ArrayList<>();
        ArrayList<Menu> all = menuDAO.getAll();
        for (Menu m : all) {
            allMenu.add(new MenuDTO(m.getmCode(), m.getDish(), m.getDes(), m.getMeal(), m.getPrice(), m.getcCode(), m.getUserName()));
        }
        return allMenu;
    }

    @Override
    public ResultSet getImage(String mCode) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = menuDAO.getImageMenu(mCode);
        return resultSet;
    }

    @Override
    public boolean save(MenuDTO dto) throws SQLException, ClassNotFoundException {
        return menuDAO.save(new Menu(dto.getmCode(), dto.getDish(), dto.getDes(), dto.getMeal(), dto.getPrice(), dto.getcCode(), dto.getUserName()));
    }

    @Override
    public boolean update(MenuDTO dto) throws SQLException, ClassNotFoundException {
        return menuDAO.update(new Menu(dto.getmCode(), dto.getDish(), dto.getDes(), dto.getMeal(), dto.getPrice(), dto.getcCode(), dto.getUserName()));
    }

    @Override
    public boolean delete(String mCode) throws SQLException, ClassNotFoundException {
        return menuDAO.delete(mCode);
    }

    @Override
    public MenuDTO search(String mCode) throws SQLException, ClassNotFoundException {
        Menu m = menuDAO.search(mCode);
        MenuDTO menuDTO = new MenuDTO(m.getmCode(), m.getDish(), m.getDes(), m.getMeal(), m.getPrice(), m.getcCode(), m.getUserName());
        return menuDTO;
    }

    @Override
    public ArrayList<MenuDTO> getAllApp(String course, String meal) throws SQLException, ClassNotFoundException {
        ArrayList<MenuDTO> allMenu = new ArrayList<>();
        ArrayList<Menu> all = menuDAO.getAllApp(course, meal);
        for (Menu m : all) {
            allMenu.add(new MenuDTO(m.getDish(), m.getPrice()));
        }
        return allMenu;
    }

}
