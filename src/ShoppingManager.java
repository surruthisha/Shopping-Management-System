interface ShoppingManager {
    void displayProductList();

    void addToCart(Product product, ShoppingCart cart);

    void removeFromCart(Product product, ShoppingCart cart);

    void displayCart(ShoppingCart cart);

    void displayTotalCost(ShoppingCart cart);
}