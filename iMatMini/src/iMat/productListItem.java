package iMat;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductListItem extends AnchorPane {

    private final IMatDataHandlerWrapper backend = IMatDataHandlerWrapper.getInstance();
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
    private Label otherInfoLabel;




    public ProductListItem(Product product) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Add information about product.
        this.product = product;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        productNameLabel.setText(product.getName());

        priceLabel.setText(product.getPrice() + " kr / " + product.getUnitSuffix());

        productImageView.setImage(backend.getFXImage(product,
                                                             productImageView.getFitWidth(),
                                                             productImageView.getFitHeight()));


        if (product.isEcological()) {
         //   Image ecoImage = new Image("imat/resources/ecological_icon");
           // ecoFriendlyImageView.setImage(ecoImage);
            otherInfoLabel.setText("Denna vara är ekologisk.\n" + "ProduktID: " + product.getProductId());
        } else {
            otherInfoLabel.setText("ProduktID: " + product.getProductId());
        }

    }




    public Product getProduct() {
        return product;
    }
}
