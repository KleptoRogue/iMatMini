package iMat;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

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

    // Count products added to cart
    private int productCounter = 0;


    public ProductItem(Product product, IMatController controller) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ProductItem.fxml"));
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
    public void onAddFavoriteEvent(Event event) {
        model.addFavorite(product);
        System.out.println("Favorited: " + product);
        favoritedAnchorPane.toFront();
    }

    @FXML
    public void onRemoveFavoriteEvent(Event event) {
        model.removeFavorite(product);
        System.out.println("Unfavorited: " + product);
        unFavoritedAnchorPane.toFront();
    }

    @FXML
    public void onAddClickEvent(Event event) {
        //TODO Connect to backend to update cart
        addRemoveProductAnchorPane.toFront();
    }

    @FXML
    public void onRemoveClickEvent(Event event) {
        // TODO Connect to backend to update cart
        addProductAnchorPane.toFront();
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
