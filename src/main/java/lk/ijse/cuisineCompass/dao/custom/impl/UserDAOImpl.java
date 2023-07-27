package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.UserDAO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class  UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO user (user_name, password, emp_id, job_role) VALUES (?, ?, ?, ?)", entity.getUserName(), entity.getPassword(), entity.getId(), entity.getJobRole());
        return isSaved;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE user SET user_name = ?, password = ?, job_role = ? WHERE emp_id = ?", entity.getUserName(), entity.getPassword(), entity.getJobRole(), entity.getId());
        return isUpdated;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM user WHERE emp_id = ?",id);
        return isDeleted;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> allUsers = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM user");

        while (rst.next()) {
            User user = new User(rst.getString("user_name"), rst.getString("password"), rst.getString("emp_id"), rst.getString("job_role"));
            allUsers.add(user);
        }
        return allUsers;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM user WHERE emp_id=?", id + "");
        rst.next();
        return new User(rst.getString("user_name"), rst.getString("password"),id + "", rst.getString("job_role"));
    }

    @Override
    public boolean valid(String userName, String pw) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM user WHERE user_name = ? AND password = ? AND (job_role = ? OR job_role = ?)", userName,pw,"Head Chef", "Sous Chef");

        if(rst.next()) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public ResultSet getImage(String userName) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT image FROM image_user WHERE user_name = ?", userName);

        if(rst.next()){
            return rst;
        }
        return null;
    }

    @Override
    public boolean isSup(String userName, String pw) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM user WHERE user_name = ? AND password = ? AND job_role = ? ", userName, pw, "Chef de Partie");
        if(rst.next()){
            return true;
        }
        return false;
    }
}
