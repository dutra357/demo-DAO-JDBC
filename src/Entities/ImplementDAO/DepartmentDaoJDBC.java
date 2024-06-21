package Entities.ImplementDAO;
import DataBase.DataBase;
import Entities.Department;
import Entities.ModelDAO.DepartmentDAO;
import java.sql.*;
import java.util.List;
import DataBase.DbException;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection connection;
    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        ResultSet rs;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO department\n" +
                            "(Name) \n" +
                            "VALUES \n" +
                            "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DataBase.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected ERROR! No rows affected.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DataBase.closeStatement(st);
        }
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
