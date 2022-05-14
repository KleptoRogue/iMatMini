package iMat;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

public class ProductListItem {

    private IMatDataHandlerWrapper parentController;
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




    public ProductListItem(Product product, IMatDataHandlerWrapper controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Add information about product.
        this.parentController = controller;

        this.productNameLabel.setText(product.getName());

        this.priceLabel.setText(product.getPrice() + " " + product.getUnitSuffix());

        this.productImageView.setImage(controller.getFXImage(product,
                                                             productImageView.getFitWidth(),
                                                             productImageView.getFitHeight()));


        if (product.isEcological()) {
            Image ecoImage = new Image("iMat/resources/ecological_icon");
            this.ecoFriendlyImageView.setImage(ecoImage);
            this.otherInfoLabel.setText("Denna vara är ekologisk.\n" + "ProduktID: " + product.getProductId());
        } else {
            this.otherInfoLabel.setText("ProduktID: " + product.getProductId());
        }

    }




    public Product getProduct() {
        return product;
    }
}
