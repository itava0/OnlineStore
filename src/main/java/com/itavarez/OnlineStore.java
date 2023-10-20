package com.itavarez;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OnlineStore {

    public static Scanner SCANNER = new Scanner(System.in);
    public static HashMap<String, Product> INVENTORY =  new HashMap<>();

    private static final List<Product> cartItems = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        homeScreen();


    }

    public static void homeScreen() throws IOException {
        System.out.println("Welcome to Ebay");
        System.out.println("How can we help you today?");
        System.out.println("\t(1)-Display Products");
        System.out.println("\t(2)-Display Cart");
        System.out.println("\t(3)-Exit the Application");


        System.out.print("Enter # Choice: ");
        int userChoice = SCANNER.nextInt();


        switch (userChoice) {
            case 1:
                loadInventory();
                displayProducts();
                break;
            case 2:
                displayCart();
                break;
            case 3:
                break;
        }
    }

    public static void loadInventory() throws IOException {

        BufferedReader readFile = new BufferedReader(new FileReader("src/main/resources/products.csv"));
        String input;
        String productSku;
        String productName;
        double productPrice;
        String productDepartment;

        while((input = readFile.readLine()) != null){
            String[] productList = input.split("\\|");
            if(!productList[0].equals("SKU")) {
                productSku = productList[0];
                productName = productList[1];
                productPrice = Double.parseDouble(productList[2]);
                productDepartment = productList[3];
                INVENTORY.put(productName.toLowerCase().replaceAll("\\s", ""), new Product(productSku, productName, productPrice, productDepartment));
            }
        }
        readFile.close();
    }

    public static void displayProducts() throws IOException {

        System.out.println("We carry the following inventory: ");
        for (Product product : INVENTORY.values()) {
            System.out.printf("Sku: %s | Product: %s | Price: $%.2f | Department: %s\n",
                    product.getSKU(), product.getPRODUCT_NAME(), product.getPRODUCT_PRICE(), product.getPRODUCT_DEPARTMENT());
        }
         while (true) {
             System.out.print("How can we help you? \n");
             System.out.println("\t(1)-Search/filter for a product");
             System.out.println("\t(2)-Add a product to cart");
             System.out.println("\t(3)-Return to Home Screen");
             System.out.print("Enter # Choice: ");
             int choice = SCANNER.nextInt();

             switch (choice) {
                 case 1:
                     while (true) {
                         SCANNER.nextLine();
                         System.out.print("What item are you interested in? ");
                         String item = SCANNER.nextLine().trim().toLowerCase().replaceAll("\\s", "");
                         System.out.println(item);
                         System.out.println(INVENTORY.keySet());
                         Product matchedProduct = INVENTORY.get(item);
                         if (matchedProduct == null) {
                             System.out.print("We don't carry that product");
                             return;
                         }
                         System.out.printf("We carry %s and the price is $%.2f\n",
                                 matchedProduct.getPRODUCT_NAME(), matchedProduct.getPRODUCT_PRICE());

                         System.out.println("Do you want to search again? (yes/no)");
                         String searchAgain = SCANNER.nextLine().trim();

                         if ("no".equalsIgnoreCase(searchAgain)) {
                             break;
                         }
                     }
                     break;
                 case 2:
                     SCANNER.nextLine();
                     System.out.print("Enter the product name you want to add to your cart: ");
                     String productName = SCANNER.nextLine().trim().toLowerCase().replaceAll("\\s", "");
                     Product productToAdd = INVENTORY.get(productName);
                     if (productToAdd != null) {
                         addProductToCart(productToAdd);
                     } else {
                         System.out.println("Product not found in inventory.");
                     }
                     break;
                 case 3:
                     homeScreen();
                     break;
             }
         }
    }

    public static void addProductToCart(Product product) {
        cartItems.add(product);
        System.out.printf("%s has been added to your cart.\n", product.getPRODUCT_NAME());
    }

    public static void displayCart() throws IOException {
        if(cartItems.isEmpty()) {
            System.out.println("Your shopping cart is empty\n");
            homeScreen();
        };

        System.out.println("Your shopping cart contains the following items:");
        for (Product product : cartItems) {
            System.out.printf("Product: %s | Price: $%.2f\n", product.getPRODUCT_NAME(), product.getPRODUCT_PRICE());
        }

        System.out.print("How can we help you? \n");
        System.out.println("\t(1)-Check out");
        System.out.println("\t(2)-Remove Product");
        System.out.println("\t(3)-Return to Home Screen");
        System.out.print("Enter # Choice: ");
        int choice = SCANNER.nextInt();

        switch (choice) {
            case 1:
                double totalCost = 0;
                for (Product product : cartItems) {
                    totalCost += product.getPRODUCT_PRICE();
                }
                System.out.printf("Your total cost is: $%.2f\n", totalCost);
                System.out.println("Thank you for shopping with us!");
                SCANNER.close();
                System.exit(0);
                break;
            case 2:
                SCANNER.nextLine();
                System.out.print("Which item would you like to remove from your shopping cart ");
                String productName = SCANNER.nextLine().trim().toLowerCase().replaceAll("\\s", "");
                Product matchedProduct = INVENTORY.get(productName);
                if (cartItems.remove(matchedProduct )) {
                    System.out.printf("%s has been removed from your cart.\n", matchedProduct .getPRODUCT_NAME());
                } else {
                    System.out.println("Product not found in your cart.");
                }
                break;
            case 3:
                homeScreen();
                break;
        }
    }
}
