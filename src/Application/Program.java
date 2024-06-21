package Application;
import Entities.Department;
import Entities.FactoryDAO;
import Entities.ModelDAO.SellerDAO;
import Entities.Seller;
import java.io.IOException;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {

        SellerDAO sellerDAO = FactoryDAO.createSellerDao();

        System.out.println("\n=== TEST 1: Seller findById ===");
        Seller seller1 = sellerDAO.findById(3);
        System.out.println(seller1);

        System.out.println("\n=== TEST 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDAO.findByDepartment(department);

        list.forEach(System.out::println);

    }
}
