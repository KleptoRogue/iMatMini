package iMat;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductItem extends AnchorPane {


    private IMatController parentcontroller;
    private Product product;

    private IMatDataHandlerWrapper model = IMatDataHandlerWrapper.getInstance();
    ProductDescriptionLightBox PD = new ProductDescriptionLightBox(product, parentcontroller);

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
    private AnchorPane productDesciption;

    @FXML
    private AnchorPane productItemAnchorPane;

    @FXML
    private AnchorPane unFavoritedAnchorPane;

    @FXML
    private AnchorPane favoritedAnchorPane;

    @FXML
    private AnchorPane hideFavoriteIconAnchorPane;

    @FXML
    private TextField productCounterTextField;

    ShoppingItem shoppingItem;

    public ProductItem(Product product, IMatController controller) {
        initializeFXML();
        this.product = product;
        this.parentcontroller = controller;
        initializeNewShoppingItem(product);
        initializeProductInformation();
        initializeProductCounterListener();
    }

    public ProductItem(ShoppingItem item, IMatController controller) {
        initializeFXML();
        this.product = item.getProduct();
        this.shoppingItem = item;
        this.parentcontroller = controller;
        initializeProductInformation();
        initializeProductCounterListener();
    }


    private void initializeNewShoppingItem(Product product) {
        int amount = 0;
        for (int i = 0; i < model.getShoppingCart().getItems().size(); i++){
            if(model.getShoppingCart().getItems().get(i).getProduct().getName().equals(this.product.getName()))
                amount = (int) model.getShoppingCart().getItems().get(i).getAmount();
        }

        this.shoppingItem = new ShoppingItem(product);
        this.shoppingItem.setAmount(amount);

        if (this.shoppingItem.getAmount() > 0){
            addRemoveProductAnchorPane.toFront();
            updateCounterTextField();
        }
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

        productImageView.onMouseClickedProperty().set(event -> openProductDescription(product,parentcontroller));

    }

    private void openProductDescription(Product product,IMatController parentcontroller) {
        parentcontroller.setPD(PD);
        PD.ProductDescriptionItem(product,parentcontroller);
        parentcontroller.openProductDescriptionLB();
    }


    private void initializeProductInformation() {
        productNameLabel.setText(product.getName());

        priceLabel.setText(product.getPrice() + " kr / " + product.getUnitSuffix());

        productImageView.setImage(model.getFXImage(product,
                productImageView.getFitWidth(),
                productImageView.getFitHeight()));

        
        if (model.getIsLoggedIn() && model.isFavorite(this.product)) {
            favoritedAnchorPane.toFront();
        } else if (model.getIsLoggedIn() && !model.isFavorite(this.product)) {
            unFavoritedAnchorPane.toFront();
        } else {
            hideFavoriteIconAnchorPane.toFront();
        }

        if (product.isEcological()) {
            //Image ecoImage = new Image("ecological_icon.png");
            //ecoFriendlyImageView.setImage(ecoImage);
            ecoLabel.setText("Ekologisk");
        }

        if (shoppingItem.getAmount() > 0) {
            addRemoveProductAnchorPane.toFront();
            updateCounterTextField();
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
        if (shoppingItem.getAmount() == 0) {
            addRemoveProductAnchorPane.toFront();
            model.getShoppingCart().addItem(shoppingItem);
        }

        shoppingItem.setAmount(shoppingItem.getAmount() + 1);
        updateCounterTextField();
        System.out.println("product: " + shoppingItem.getProduct());
        System.out.println("Amount: " + shoppingItem.getAmount());

    }

    @FXML
    public void removeProductClickEvent(Event event) {
        shoppingItem.setAmount(shoppingItem.getAmount() - 1);

        if (shoppingItem.getAmount() == 0) {
            addProductAnchorPane.toFront();
            model.getShoppingCart().removeItem(shoppingItem);
        }
        updateCounterTextField();
        System.out.println("product: " + shoppingItem.getProduct());
        System.out.println("Amount: " + shoppingItem.getAmount());
    }

    private void initializeProductCounterListener() {
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

    public int getProductID() {
        return product.getProductId();
    }

    private void updateCounterTextField() {
        productCounterTextField.clear();
        productCounterTextField.setText( ((int) shoppingItem.getAmount()) + "");
    }

}