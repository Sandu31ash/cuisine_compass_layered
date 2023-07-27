package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.UserBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.UserDAO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.UserDTO;
import lk.ijse.cuisineCompass.entity.Employee;
import lk.ijse.cuisineCompass.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUserName(), dto.getPassword(), dto.getId(), dto.getJobRole()));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(dto.getUserName(), dto.getPassword(), dto.getId(), dto.getJobRole()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public boolean valid(String userName, String pw) throws SQLException, ClassNotFoundException {
        return userDAO.valid(userName,pw);
    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUsers= new ArrayList<>();
        ArrayList<User> all = userDAO.getAll();
        for (User u: all) {
            allUsers.add(new UserDTO(u.getUserName(), u.getPassword(), u.getId(), u.getJobRole()));
        }
        return allUsers;
    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        User u = userDAO.search(id);
        UserDTO userDTO = new UserDTO(u.getUserName(), u.getPassword(), u.getId(), u.getJobRole());
        return userDTO;
    }

    @Override
    public ResultSet getImage(String userName) throws SQLException, ClassNotFoundException {
        return userDAO.getImage(userName);
    }

    @Override
    public boolean isSup(String userName, String pw) throws SQLException, ClassNotFoundException {
        return userDAO.isSup(userName, pw);
    }
}
