package lk.ijse.cuisineCompass.bo.custom.impl;

import lk.ijse.cuisineCompass.bo.custom.EmployeeBO;
import lk.ijse.cuisineCompass.dao.DAOFactory;
import lk.ijse.cuisineCompass.dao.custom.EmployeeDAO;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getId(),dto.getName(),dto.getJobRole(),dto.getEmail(), dto.getContact()));
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getId(),dto.getName(),dto.getJobRole(),dto.getEmail(), dto.getContact()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
        Employee e = employeeDAO.search(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(e.getId(), e.getName(), e.getJobRole(), e.getEmail(), e.getContact());
        return employeeDTO;
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees= new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee e : all) {
            allEmployees.add(new EmployeeDTO(e.getId(),e.getName(),e.getJobRole(),e.getEmail(),e.getContact()));
        }
        return allEmployees;
    }

}
