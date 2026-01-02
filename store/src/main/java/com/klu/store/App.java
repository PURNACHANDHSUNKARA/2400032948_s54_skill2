package com.klu.store;

import java.util.List;
import java.util.Scanner;

import com.klu.store.dao.ProductDAO;
import com.klu.store.entity.Product;

public class App {

    public static void main(String[] args) {

        ProductDAO pdao = new ProductDAO();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

            case 1:
                sc.nextLine();
                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Product Description: ");
                String desc = sc.nextLine();

                System.out.print("Enter Product Price: ");
                double price = sc.nextDouble();

                Product p = new Product(name, desc, price);
                pdao.saveProduct(p);
                System.out.println("Product Added Successfully");
                break;

            case 2:
                List<Product> plist = pdao.getProducts();
                if (plist.isEmpty()) {
                    System.out.println("No Products Found");
                } else {
                    for (Product pr : plist) {
                        System.out.println(pr);
                    }
                }
                break;

            case 3:
                System.out.print("Enter Product ID to Update: ");
                int uid = sc.nextInt();
                Product upd = pdao.getByProductId(uid);

                if (upd != null) {
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    upd.setName(sc.nextLine());

                    System.out.print("Enter New Description: ");
                    upd.setDescription(sc.nextLine());

                    System.out.print("Enter New Price: ");
                    upd.setPrice(sc.nextDouble());

                    boolean updated = pdao.updateproduct(upd);
                    if (updated)
                        System.out.println("Product Updated Successfully");
                    else
                        System.out.println("Update Failed");
                } else {
                    System.out.println("Product Not Found");
                }
                break;

            case 4:
                System.out.print("Enter Product ID to Delete: ");
                int did = sc.nextInt();
                boolean deleted = pdao.deleteProduct(did);

                if (deleted)
                    System.out.println("Product Deleted Successfully");
                else
                    System.out.println("Delete Failed / Product Not Found");
                break;

            case 5:
                System.out.println("Exiting Application...");
                break;

            default:
                System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 5);

        sc.close();
    }
 }

