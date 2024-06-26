package Entities.ImplementDAO;
import DataBase.DataBase;
import DataBase.DbException;
import Entities.Department;
import Entities.ModelDAO.SellerDAO;
import Entities.Seller;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {

    private Connection connection;
    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement st = null;
        ResultSet rs;
        try {
            st = connection.prepareStatement(
                "INSERT INTO seller\n" +
                        "(Name, Email, BirthDate, BaseSalary, DepartmentId) \n" +
                        "VALUES \n" +
                        "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    seller.setId(id);
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
    public void update(Seller seller) {
        PreparedStatement st = null;
        ResultSet rs;
        try {
            st = connection.prepareStatement(
                    "UPDATE seller \n" +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? \n" +
                            "WHERE Id = ?");
            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());
            st.setInt(6, seller.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DataBase.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM seller \n" +
                    "WHERE Id = ?");
            st.setInt(1, id);

            int rows = st.executeUpdate();

            if (rows == 0) {
                System.out.println("Nothing to delete.");
            } else {
                System.out.println("Delete completed!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DataBase.closeStatement(st);
        }
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n" +
                            "FROM seller INNER JOIN department \n" +
                            "ON seller.DepartmentId = department.Id \n" +
                            "WHERE seller.Id = ?");

            st.setInt(1, id);

            //'rs' returns a table.
            rs = st.executeQuery();
            if (rs.next()) {
                Department department = instantiateDepartment(rs);
                return instantiateSeller(rs, department);
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DataBase.closeStatement(st);
            DataBase.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n" +
                            "FROM seller INNER JOIN department \n" +
                            "ON seller.DepartmentId = department.Id\n" +
                            "ORDER BY Name");

            //'rs' returns a table.
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dept = map.get(rs.getInt("DepartmentId"));
                if (dept == null) {
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dept);
                }
                Seller seller = instantiateSeller(rs, dept);
                list.add(seller);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DataBase.closeStatement(st);
            DataBase.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n" +
                            "FROM seller INNER JOIN department \n" +
                            "ON seller.DepartmentId = department.Id\n" +
                            "WHERE DepartmentId = ?\n" +
                            "ORDER BY Name");
            st.setInt(1, department.getId());

            //'rs' returns a table.
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dept = map.get(rs.getInt("DepartmentId"));
                if (dept == null) {
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dept);
                }
                Seller seller = instantiateSeller(rs, dept);
                list.add(seller);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DataBase.closeStatement(st);
            DataBase.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(department);
        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("DepartmentId"));
        department.setName(rs.getString("DepName"));

        return department;
    }
}
