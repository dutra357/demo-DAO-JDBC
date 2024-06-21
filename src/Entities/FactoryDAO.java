package Entities;
import DataBase.DataBase;
import Entities.ImplementDAO.DepartmentDaoJDBC;
import Entities.ImplementDAO.SellerDaoJDBC;
import Entities.ModelDAO.DepartmentDAO;
import Entities.ModelDAO.SellerDAO;

import java.io.IOException;

public class FactoryDAO {

    public static SellerDAO createSellerDao() throws IOException {
        return new SellerDaoJDBC(DataBase.getConnection());
    }

    public static DepartmentDAO createDepartmentDao() throws IOException {
        return new DepartmentDaoJDBC(DataBase.getConnection());
    }
}
