package Application;

import Entities.Department;
import Entities.FactoryDAO;
import Entities.ModelDAO.SellerDAO;
import Entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Department department = new Department(1, "Books");
        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.00, department);

        SellerDAO sellerDAO = FactoryDAO.createSellerDao();


        System.out.println(department);
        System.out.println(seller);
    }
}
