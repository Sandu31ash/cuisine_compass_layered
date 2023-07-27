package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.MenuDAO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.Inventory;
import lk.ijse.cuisineCompass.entity.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public boolean save(Menu entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO menu (menu_code, dish, description, meal, price, category_code, user_name) VALUES (?,?,?,?,?,?,?)", entity.getmCode(), entity.getDish(), entity.getDes(), entity.getMeal(), entity.getPrice(), entity.getcCode(), entity.getUserName());
        return isSaved;
    }

    @Override
    public boolean update(Menu entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE menu SET dish = ?, description = ?, meal = ?, price = ?, category_code = ?, user_name = ? WHERE menu_code = ?) VALUES (?,?,?,?,?,?,?)", entity.getDish(), entity.getDes(), entity.getMeal(), entity.getPrice(), entity.getcCode(), entity.getUserName(), entity.getmCode());
        return isUpdated;
    }

    @Override
    public boolean delete(String mCode) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM menu WHERE menu_code = ?", mCode);
        return isDeleted;
    }

    @Override
    public ArrayList<Menu> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Menu> allMenu = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM menu");

        while (rst.next()) {
            Menu menu = new Menu(rst.getString("menu_code"), rst.getString("dish"), rst.getString("description"), rst.getString("meal"), rst.getDouble("price"), rst.getString("category_code"), rst.getString("user_name"));
            allMenu.add(menu);
        }
        return allMenu;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Menu search(String mCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM menu WHERE menu_code = ?", mCode + "");
        rst.next();
        return new Menu(mCode + "", rst.getString("dish"), rst.getString("description"),rst.getString("meal"), rst.getDouble("price"), rst.getString("category_code"), rst.getString("user_name"));
    }

    @Override
    public ResultSet getImageMenu(String mCode) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = SQLUtil.execute("SELECT image FROM image_menu WHERE id = ?", mCode);
        return rst;*/
        ResultSet rst = SQLUtil.execute("SELECT image FROM image_menu WHERE id = ?", mCode);

        if(rst.next()){
            return rst;
        }
        return null;
    }

    @Override
    public ArrayList<Menu> getAllApp(String course, String meal) throws SQLException, ClassNotFoundException {
        ArrayList<Menu> allMenu = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM menu WHERE description LIKE ?", course);

        while (rst.next()) {
            if(rst.getString("meal").equalsIgnoreCase(meal)) {
                Menu menu = new Menu(rst.getString("dish"), rst.getDouble("price"));
                allMenu.add(menu);
            }
        }
        return allMenu;
    }

}
