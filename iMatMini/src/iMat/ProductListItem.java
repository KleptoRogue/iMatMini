package iMat;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductListItem extends AnchorPane {


    private IMatController parentcontroller;
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
    private Button addProductButton;

    @FXML
    private AnchorPane unFavoritedAnchorPane;

    @FXML
    private AnchorPane favoritedAnchorPane;

    @FXML
    private TextField productCounterTextField;

    // Count products added to cart
    private int productCounter = 0;


    public ProductListItem(Product product, IMatController controller) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/productItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Add information about product.
        this.product = product;
        this.parentcontroller = controller;

        productNameLabel.setText(product.getName());

        priceLabel.setText(product.getPrice() + " kr / " + product.getUnitSuffix());

        productImageView.setImage(parentcontroller.getFXImage(product,
                                                             productImageView.getFitWidth(),
                                                             productImageView.getFitHeight()));


        if (parentcontroller.isProductFavorited(this.product)) {
            favoritedAnchorPane.toFront();
        }


        if (product.isEcological()) {
            //Image ecoImage = new Image("ecological_icon.png");
            //ecoFriendlyImageView.setImage(ecoImage);
            ecoLabel.setText("Ekologisk");
        }



    }


    @FXML
    public void onAddFavoriteEvent(Event event) {
        parentcontroller.addFavorite(product, favoritedAnchorPane);
    }

    @FXML
    public void onRemoveFavoriteEvent(Event event) {
        parentcontroller.removeFavorite(product, unFavoritedAnchorPane);
    }

    @FXML
    public void onAddClickEvent(Event event) {
        parentcontroller.addProduct(product, addRemoveProductAnchorPane);
    }

    @FXML
    public void onRemoveClickEvent(Event event) {
        parentcontroller.removeProduct(product, addProductAnchorPane);
    }
    public Product getProduct() {
        return product;
    }


    public int getProductCounter() {
        return productCounter;
    }

    public void setProductCounter(int productCounter) {
        this.productCounter = productCounter;
    }
}
