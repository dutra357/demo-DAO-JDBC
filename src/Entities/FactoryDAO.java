package Entities;
import Entities.ImplementDAO.SellerDaoJDBC;
import Entities.ModelDAO.SellerDAO;

public class FactoryDAO {

    public static SellerDAO createSellerDao() {
        return new SellerDaoJDBC();
    }
}
