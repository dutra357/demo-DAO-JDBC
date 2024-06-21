package Entities.ImplementDAO;

import Entities.Department;
import Entities.ModelDAO.DepartmentDAO;

import java.sql.Connection;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection connection;
    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
