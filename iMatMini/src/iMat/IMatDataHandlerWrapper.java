package iMat;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IMatDataHandlerWrapper {

    // Categories
    private final Map<String, List<Product>> productItemMap = new HashMap<>();

    private static IMatDataHandlerWrapper instance = null;

    private final IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    private IMatDataHandlerWrapper() {
        // No instantiation
    }

    private void initiateProductCategories() {
        // TODO
         /*
            POD,
            BREAD,
            BERRY,
            CITRUS_FRUIT,
            HOT_DRINKS,
            COLD_DRINKS,
            EXOTIC_FRUIT,
            FISH,
            VEGETABLE_FRUIT,
            CABBAGE,
            MEAT,
            DAIRIES,
            MELONS,
            FLOUR_SUGAR_SALT,
            NUTS_AND_SEEDS,
            PASTA,
            POTATO_RICE,
            ROOT_VEGETABLE,
            FRUIT,
            SWEET,
            HERB;
     */
        List<Product> products = getProducts();

        List<Product> vegetables = new ArrayList<>();
        // Basvaror
        List<Product> normalGoods = new ArrayList<>();
        //  Mejeri
        List<Product> dairyProducts = new ArrayList<>();
        List<Product> fruitProducts = new ArrayList<>();
        List<Product> meatProducts = new ArrayList<>();
        List<Product> vegetarianProducts = new ArrayList<>();
        List<Product> fishProducts = new ArrayList<>();
        List<Product> sweetsProducts = new ArrayList<>();
        List<Product> spicesProducts = new ArrayList<>();
    }

    // Singleton pattern
    public static IMatDataHandlerWrapper getInstance() {
        if (instance == null) {
            instance = new IMatDataHandlerWrapper();
        }
        return instance;
    }

    // Saves all information when exiting application
    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    // Returns customer object: Setting / getting customer information
    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    // Returns true if its the first run.
    public boolean isFirstRun() {
        return iMatDataHandler.isFirstRun();
    }

    public void resetFirstRun() {
        iMatDataHandler.resetFirstRun();
    }

    // Resets everything to initial state
    public void reset() {
        iMatDataHandler.reset();
    }

    // Returns true if all information about the customer has been given.
    public boolean isCustomerComplete() {
        return iMatDataHandler.isCustomerComplete();
    }

    // Returns the single user object holding information about the (optional) user.
    public User getUser() {
        return iMatDataHandler.getUser();
    }
    public void setUser(User user) {
        iMatDataHandler.getUser().setUserName(user.getUserName());
        iMatDataHandler.getUser().setPassword(user.getPassword());
    }

    private boolean isLoggedIn;
    public void setIsLoogedIn(boolean isIn){
        isLoggedIn = isIn;
    }
    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }

    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    /*
    Creates an order from the current contents of the shoppingcart.
     - Clears the shopping cart and adds the order to "orders" list
    */
    public Order placeOrder() {
        return iMatDataHandler.placeOrder();
    }


    // Returns a list of past orders (order history)
    public List<Order> getOrders() {
        return iMatDataHandler.getOrders();
    }

    // Returns the product with the given idNbr.
    public Product getProduct(int idNbr) {
        return iMatDataHandler.getProduct(idNbr);
    }

    // Returns all products
    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    // Returns products based on a category
    public List<Product> getProducts(ProductCategory pc) {
        return iMatDataHandler.getProducts(pc);
    }

    // Search for products
    public List<Product> findProducts(String s) {
        return iMatDataHandler.findProducts(s);
    }

    public void addProduct(Product p) {
        iMatDataHandler.addProduct(p);
    }

    public void removeProduct(Product p) {
        iMatDataHandler.removeProduct(p);
    }

    public void addFavorite(Product p) {
        iMatDataHandler.addFavorite(p);
    }

    public void addFavorite(int idNbr) {
        iMatDataHandler.addFavorite(idNbr);
    }

    public void removeFavorite(Product p) {
        iMatDataHandler.removeFavorite(p);
    }

    public boolean isFavorite(Product p) {
        return iMatDataHandler.isFavorite(p);
    }

    public List<Product> getFavorites() {
        return iMatDataHandler.favorites();
    }

    // Returns true if the given Product object p has an image associated with.
    public boolean hasImage(Product p) {
        return iMatDataHandler.hasImage(p);
    }

    // Returns a javafx.scene.image.Image with a full-sized image for the product P.
    public Image getFXImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    // Returns a javafx.scene.image.Image with the specified width and height for the product P.
    public Image getFXImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    // Returns a javafx.scene.image.Image with the specified width and height for the product P.
    public Image getFXImage(Product p, double requestedWidth, double requestedHeight, boolean preserveRatio) {
        return iMatDataHandler.getFXImage(p, requestedWidth, requestedHeight, preserveRatio);
    }

    public void setProductImage(Product p, File filename) {
        iMatDataHandler.setProductImage(p, filename);
    }
}