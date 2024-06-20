package Application;
import Entities.Department;
import Entities.FactoryDAO;
import Entities.ModelDAO.SellerDAO;
import Entities.Seller;
import java.io.IOException;
import java.util.Date;

public class Program {
    public static void main(String[] args) throws IOException {

        Department department = new Department(1, "Books");
        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.00, department);


        SellerDAO sellerDAO = FactoryDAO.createSellerDao();


        Seller seller1 = sellerDAO.findById(3);

        System.out.println(department);
        System.out.println(seller1);
    }
}
