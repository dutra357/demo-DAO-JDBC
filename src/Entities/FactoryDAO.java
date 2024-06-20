package Entities;
import DataBase.DataBase;
import Entities.ImplementDAO.SellerDaoJDBC;
import Entities.ModelDAO.SellerDAO;

import java.io.IOException;

public class FactoryDAO {

    public static SellerDAO createSellerDao() throws IOException {
        return new SellerDaoJDBC(DataBase.getConnection());
    }
}
