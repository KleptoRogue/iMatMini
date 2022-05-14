package iMat;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class productListItem {

    @FXML
    private ImageView productImageView;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label otherInfoLabel;

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane addRemoveProductAnchorPane;

    @FXML
    private AnchorPane addProductAnchorPane;


    public productListItem() {
        this.productImageView = productImageView;
        this.productNameLabel = productNameLabel;
        this.priceLabel = priceLabel;
        this.otherInfoLabel = otherInfoLabel;
    }

    public void setProductImageView(ImageView productImageView) {
        this.productImageView = productImageView;
    }

    public void setProductNameLabel(Label productNameLabel) {
        this.productNameLabel = productNameLabel;
    }

    public void setPriceLabel(Label priceLabel) {
        this.priceLabel = priceLabel;
    }

    public void setOtherInfoLabel(Label otherInfoLabel) {
        this.otherInfoLabel = otherInfoLabel;
    }

    public ImageView getProductImageView() {
        return productImageView;
    }

    public Label getProductNameLabel() {
        return productNameLabel;
    }

    public Label getPriceLabel() {
        return priceLabel;
    }

    public Label getOtherInfoLabel() {
        return otherInfoLabel;
    }

    public AnchorPane getAddRemoveProductAnchorPane() {
        return addRemoveProductAnchorPane;
    }

    public AnchorPane getAddProductAnchorPane() {
        return addProductAnchorPane;
    }
}
