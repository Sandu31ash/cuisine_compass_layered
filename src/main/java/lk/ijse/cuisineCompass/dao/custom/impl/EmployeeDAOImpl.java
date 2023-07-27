package lk.ijse.cuisineCompass.dao.custom.impl;

import lk.ijse.cuisineCompass.dao.SQLUtil;
import lk.ijse.cuisineCompass.dao.custom.EmployeeDAO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("INSERT INTO employee (emp_id, name, job_role, email, contact) VALUES (?,?,?,?,?)", entity.getId(), entity.getName(), entity.getJobRole(), entity.getEmail(), entity.getContact());
        return isSaved;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtil.execute("UPDATE employee SET name = ?, job_role = ?, email = ?, contact = ? WHERE emp_id = ?", entity.getName(), entity.getJobRole(), entity.getEmail(), entity.getContact(), entity.getId());
        return isUpdated;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtil.execute("DELETE FROM employee WHERE emp_id = ?", id);
        return isDeleted;
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");

        while (rst.next()) {
            Employee employee = new Employee(rst.getString("emp_id"), rst.getString("name"), rst.getString("job_Role"), rst.getString("email"), rst.getString("contact"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE emp_id=?", id + "");
        rst.next();
        return new Employee(id + "", rst.getString("name"), rst.getString("job_role"),rst.getString("email"), rst.getString("contact"));
    }

}
