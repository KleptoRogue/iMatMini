package iMat;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class ProductItem extends AnchorPane {


    private IMatController parentcontroller;

    private IMatDataHandlerWrapper model = IMatDataHandlerWrapper.getInstance();
    private Product product;
    @FXML
    private ImageView productImageView;

    @FXML
    private ImageView ecoFriendlyImageView;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label ecoLabel;

    @FXML
    private AnchorPane addRemoveProductAnchorPane;

    @FXML
    private AnchorPane addProductAnchorPane;


    @FXML
    private AnchorPane unFavoritedAnchorPane;

    @FXML
    private AnchorPane favoritedAnchorPane;

    @FXML
    private TextField productCounterTextField;

    ShoppingItem shoppingItem;


    // Count products added to cart
    private int productCounter = 0;


    public ProductItem(Product product, IMatController controller) {
        initializeFXML();
        this.product = product;
        this.parentcontroller = controller;
        initializeNewShoppingItem(product);
        initializeProductInformation();
        initializeProductCounterListener();
    }

    public ProductItem(Product product, IMatController controller, ShoppingItem item) {

    }

    private void initializeNewShoppingItem(Product product) {
        this.shoppingItem = new ShoppingItem(product);
        this.shoppingItem.setAmount(0);
    }
    private void initializeFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ProductItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    private void initializeProductInformation() {
        productNameLabel.setText(product.getName());

        priceLabel.setText(product.getPrice() + " kr / " + product.getUnitSuffix());

        productImageView.setImage(model.getFXImage(product,
                productImageView.getFitWidth(),
                productImageView.getFitHeight()));


        if (model.isFavorite(this.product)) {
            favoritedAnchorPane.toFront();
        }

        if (product.isEcological()) {
            //Image ecoImage = new Image("ecological_icon.png");
            //ecoFriendlyImageView.setImage(ecoImage);
            ecoLabel.setText("Ekologisk");
        }

    }
    @FXML
    public void addFavoriteEvent(Event event) {
        model.addFavorite(product);
        System.out.println("Favorited: " + product);
        favoritedAnchorPane.toFront();
    }

    @FXML
    public void removeFavoriteEvent(Event event) {
        model.removeFavorite(product);
        System.out.println("Unfavorited: " + product);
        unFavoritedAnchorPane.toFront();
    }

    @FXML
    public void addProductClickEvent(Event event) {
        //TODO Connect to backend to update cart

        ShoppingCart cart = model.getShoppingCart();

        if (shoppingItem.getAmount() == 0) {
            addRemoveProductAnchorPane.toFront();
            cart.addItem(shoppingItem);
        }

        shoppingItem.setAmount(shoppingItem.getAmount() + 1);
        updateCounterTextField();
        System.out.println("product: " + shoppingItem.getProduct());
        System.out.println("Amount: " + shoppingItem.getAmount());

    }

    @FXML
    public void removeProductClickEvent(Event event) {
        // TODO Connect to backend to update cart
        ShoppingCart cart = model.getShoppingCart();
        shoppingItem.setAmount(shoppingItem.getAmount() - 1);

        if (shoppingItem.getAmount() == 0) {
            addProductAnchorPane.toFront();
            cart.removeItem(this.shoppingItem);
            System.out.println("Cart: " + cart.getItems());

        }
        updateCounterTextField();
        System.out.println("product: " + shoppingItem.getProduct());
        System.out.println("Amount: " + shoppingItem.getAmount());
    }

    public void initializeProductCounterListener() {
        productCounterTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(newValue){
                    //focusgained - do nothing
                }
                else{
                    System.out.println(productCounterTextField.getText());
                    try {
                        int intValue = Integer.parseInt(productCounterTextField.getText());
                        if (intValue < 0) {
                            updateCounterTextField(); // Revert to old value.
                        } else {
                            if (intValue == 0) {
                                addProductAnchorPane.toFront();
                                shoppingItem.setAmount(0);
                                model.getShoppingCart().removeItem(shoppingItem);
                                System.out.println(model.getShoppingCart().getItems());
                            } else {
                                shoppingItem.setAmount(intValue);
                                updateCounterTextField();
                                System.out.println(shoppingItem.getAmount());
                            }

                        }

                    } catch (NumberFormatException error) {
                        updateCounterTextField(); // Revert to old value
                    }
                }
            }
        });
    }
    private void updateCounterTextField() {
        productCounterTextField.clear();
        productCounterTextField.setText( ((int) shoppingItem.getAmount()) + "");
    }
}
