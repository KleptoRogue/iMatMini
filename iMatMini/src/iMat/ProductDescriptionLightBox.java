package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;


public class ProductDescriptionLightBox extends AnchorPane {

    @FXML private AnchorPane productItemAnchorPane;
    @FXML private AnchorPane productDesciption;

    @FXML private Label titleLabel;
    @FXML private Label priceLabel;
    @FXML private Label ecoLabel;

    @FXML private Button LäggTillButton;

    @FXML private ImageView detailImage;
    @FXML private ImageView star_unmarked;
    @FXML private ImageView closeImageView;

    @FXML private ToggleButton Produktbeskrivning;
    @FXML private ToggleButton Innerhållsförteckning;
    @FXML private ToggleButton Övrig_Information;

    @FXML private AnchorPane unFavoritedAnchorPane;
    @FXML private AnchorPane favoritedAnchorPane;




    private Product product;
    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;
    private ProductItem ProductItemController;


    public void ProductDescriptionItem(Product product, IMatController controller) {
        this.product = product;
        this.mainController = controller;
        initializeProductDescriptionInformation();

    }

    private void ProductDescriptionLightBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ProductDescription.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }


    private void initializeProductDescriptionInformation() {
        titleLabel.setText(product.getName());

        priceLabel.setText(product.getPrice() + " kr / " + product.getUnitSuffix());

        detailImage.setImage(wrapper.getFXImage(product,
                detailImage.getFitWidth(),
                detailImage.getFitHeight()));


        if (wrapper.isFavorite(this.product)) {
            favoritedAnchorPane.toFront();
        }

        if (product.isEcological()) {
            //Image ecoImage = new Image("ecological_icon.png");
            //ecoFriendlyImageView.setImage(ecoImage);
            ecoLabel.setText("Ekologisk");
        }

    }
/*
    public void openProductDescription(Product product) {
        new ProductItem(product,mainController);
        productDesciption.toFront();
    }

    @FXML protected void onClick(Event event){ openProductDescription(product); }
*/
}

