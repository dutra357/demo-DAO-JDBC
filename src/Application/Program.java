package Application;
import Entities.Department;
import Entities.FactoryDAO;
import Entities.ModelDAO.SellerDAO;
import Entities.Seller;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        SellerDAO sellerDAO = FactoryDAO.createSellerDao();

        System.out.println("\n=== TEST 1: Seller findById ===");
        Seller seller1 = sellerDAO.findById(3);
        System.out.println(seller1);

        System.out.println("\n=== TEST 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDAO.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println("\n=== TEST 3: Seller findAll ===");
        //List<Seller> findAll = sellerDAO.findAll();
        //findAll.forEach(System.out::println);


        System.out.println("\n=== TEST 4: Seller INSERT ===");
        //Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);
        //sellerDAO.insert(newSeller);
        //System.out.println("Inserted! New id: " + newSeller.getId());

        System.out.println("\n=== TEST 5: Seller UPDATE ===");
        //seller1 = sellerDAO.findById(1);
        //seller1.setName("Martha Wayne");
        //sellerDAO.update(seller1);
        //System.out.println("Update completed!");

        System.out.println("\n=== TEST 6: Seller DELETE ===");
        System.out.println("Enter id for delete statement: ");
        int id = sc.nextInt();
        sellerDAO.deleteById(id);

    }
}
