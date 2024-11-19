abstract class Product {
private String productID;
private String productName;
private int availableItems;
private double price;

public Product(String productID, String productName, int availableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        }



// Getters and setters

public String getProductID() {
        return productID;
        }

public String getProductName() {
        return productName;
        }

public int getAvailableItems() {
        return availableItems;
        }

public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
        }

public double getPrice() {
        return price;
        }

public void setPrice(double price) {
        this.price = price;
        }

// Abstract method to be implemented by subclasses
public abstract void displayInfo();
        }