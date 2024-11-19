import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;


class WestminsterShoppingManager implements ShoppingManager, Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> productList;

    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }



    @Override
    public void addToCart(Product product, ShoppingCart cart) {
        if (product.getAvailableItems() > 0) {
            cart.addProduct(product);
            product.setAvailableItems(product.getAvailableItems() - 1);
            System.out.println(product.getProductName() + " added to the cart.");
        } else {
            System.out.println("Sorry, " + product.getProductName() + " is out of stock.");
        }
    }

    @Override
    public void removeFromCart(Product product, ShoppingCart cart) {

    }

    @Override
    public void displayCart(ShoppingCart cart) {

    }


    @Override
    public void displayTotalCost(ShoppingCart cart) {
        System.out.println("Total Cost: $" + cart.calculateTotalCost());
    }

    // Additional methods for managing the product list
    public void addProductToCatalog(Product product) {
        productList.add(product);
    }

    public void removeProductFromCatalog(Product product) {
        productList.remove(product);
    }

    public static void main(String[] args) {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        loadProductsFromFile(shoppingManager);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n------ Westminster Shopping System Menu ------");
            System.out.println("1. Add a new product to the system");
            System.out.println("2. Delete a product from the system");
            System.out.println("3. Print the list of products");
            System.out.println("4. Save products to a file");
            System.out.println("5. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addNewProduct(shoppingManager);
                    break;
                case 2:
                    deleteProduct(shoppingManager);
                    break;
                case 3:
                    printProductList(shoppingManager);
                    break;
                case 4:
                    saveProductsToFile(shoppingManager);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a number from 1 to 5.");
            }
        }


    }

    //Get inputs from user++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private static int getUserChoice() {
        int choice = 0;
        boolean validInput = false;
        Scanner scanner= new Scanner(System.in);

        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return choice;
    }

    //Add new product++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private static void addNewProduct(WestminsterShoppingManager shoppingManager) {
        if (shoppingManager.getProductList().size() < 50) {
            System.out.println("Choose the type of product to add:");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");

            int productTypeChoice = getUserChoice();

            if (productTypeChoice == 1) {
                // Add Electronics
                shoppingManager.addProductToCatalog(createElectronics());
                System.out.println("Successfully Added");
            } else if (productTypeChoice == 2) {
                // Add Clothing
                shoppingManager.addProductToCatalog(createClothing());
                System.out.println("Successfully Added");
            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        } else {
            System.out.println("Cannot add more products. Maximum limit reached.");
        }
    }

    private static Electronics createElectronics() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Product id:");
        String productId=scanner.nextLine();
        System.out.println("Enter Product Name:");
        String productName=scanner.nextLine();
        System.out.println("Enter Number of Available Items:");
        int availableItems=scanner.nextInt();
        System.out.println("Enter Price:");
        double price=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Brand: ");
        String brand=scanner.nextLine();
        System.out.println("Enter Warranty Period:");
        int warrantyPeriod=scanner.nextInt();



        return new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);

    }

    private static Clothing createClothing() {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Product id:");
        String productId=scanner.nextLine();
        System.out.println("Enter Product Name:");
        String productName=scanner.nextLine();
        System.out.println("Enter Number of Available Items:");
        int availableItems=scanner.nextInt();
        System.out.println("Enter Price:");
        double price=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Size: ");
        String size=scanner.nextLine();
        System.out.println("Enter Color:");
        String color=scanner.nextLine();

        return new Clothing(productId, productName, availableItems, price, size, color);
    }

    //Print Product list++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public void displayProductList() {
        System.out.println("Product List:");
        for (Product product : productList) {
            product.displayInfo();
        }
    }

    public List<Product> getProductList() {
        return productList;
    }


    private static void printProductList(WestminsterShoppingManager shoppingManager) {

        List<Product> productList = shoppingManager.getProductList();

        if (!productList.isEmpty()) {

            System.out.println("\n------ Product List ------");
            for (Product product : productList) {
                product.displayInfo();
                System.out.println("Type: " + (product instanceof Electronics ? "Electronics" : "Clothing"));
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No products in the system.");
        }
    }


    //Delete Product+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private static void deleteProduct(WestminsterShoppingManager shoppingManager) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the product ID to delete: ");
        String productID = scanner.nextLine();
        Product deletedProduct = null;

        for (Product product : shoppingManager.getProductList()) {
            if (product.getProductID().equals(productID)) {
                deletedProduct = product;
                break;
            }
        }

        if (deletedProduct != null) {
            shoppingManager.removeProductFromCatalog(deletedProduct);
            System.out.println("Product deleted: " + deletedProduct.getProductName() +
                    " (" + (deletedProduct instanceof Electronics ? "Electronics" : "Clothing") + ")");
            System.out.println("Total number of products left: " + shoppingManager.getProductList().size());
        } else {
            System.out.println("Product not found with ID: " + productID);
        }
    }


    //Save file+++++++++++++++++++++++++++++++++++++++++

    private static void saveProductsToFile(WestminsterShoppingManager shoppingManager) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.dat"))) {
            oos.writeObject(shoppingManager.getProductList());
            System.out.println("Products saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    private static void loadProductsFromFile(WestminsterShoppingManager shoppingManager) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.dat"))) {
            List<Product> productList = (List<Product>) ois.readObject();
            shoppingManager.getProductList().addAll(productList);
            System.out.println("Products loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found. Starting with an empty product list.");
        }
    }






}