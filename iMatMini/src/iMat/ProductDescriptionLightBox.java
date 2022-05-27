package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;


public class ProductDescriptionLightBox extends AnchorPane {

    @FXML private AnchorPane productItemAnchorPane;
    @FXML private AnchorPane productDesciption;

    @FXML private Label titleLabel;
    @FXML private Label priceLabel;
    @FXML private Label ecoLabel;

    @FXML private TextField productCounterTextField;

    @FXML private Button LäggTillButton;

    @FXML private ImageView detailImage;
    @FXML private ImageView star_unmarked;
    @FXML private ImageView closeImageView;

    @FXML private ToggleButton Produktbeskrivning;
    @FXML private ToggleButton Innerhållsförteckning;
    @FXML private ToggleButton Övrig_Information;

    @FXML private AnchorPane unFavoritedAnchorPane;
    @FXML private AnchorPane favoritedAnchorPane;
    @FXML private AnchorPane produktbeskrivningAnchor;
    @FXML private AnchorPane innerhållsföreckningAnchor;
    @FXML private AnchorPane övrigInfoAnchor;
    @FXML private AnchorPane productDescriptionTransparentPane;
    @FXML private AnchorPane mainPane;
    @FXML private AnchorPane addRemoveProductAnchorPane;
    @FXML private AnchorPane addProductAnchorPane;


    private Product product;
    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;
    private ProductItem ProductItemController;
    ShoppingItem shoppingItem;





    @FXML
    void prodClicked(Event event) {
        övrigInfoAnchor.toFront();
    }
    @FXML
    void innerhållsföreckningAnchor(Event event) {
        innerhållsföreckningAnchor.toFront();
    }
    @FXML
    void övrigInfoClicked(Event event) {
        produktbeskrivningAnchor.toFront();
    }

    private void initializeNewShoppingItem(Product product) {
        this.shoppingItem = new ShoppingItem(product);
        this.shoppingItem.setAmount(0);
    }
    public void ProductDescriptionItem(Product product, IMatController controller) {
        this.mainController = controller;
        this.product = product;

        titleLabel.setText(product.getName());

        System.out.println(titleLabel + "TEST");

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
        initializeNewShoppingItem(product);
        initializeProductCounterListener();
    }
    @FXML
    public void addFavoriteEvent(Event event) {
        wrapper.addFavorite(product);
        System.out.println("Favorited: " + product);
        favoritedAnchorPane.toFront();
    }

    @FXML
    public void removeFavoriteEvent(Event event) {
        wrapper.removeFavorite(product);
        System.out.println("Unfavorited: " + product);
        unFavoritedAnchorPane.toFront();
    }


    @FXML
    public void addProductClickEvent(Event event) {
        //TODO Connect to backend to update cart

        ShoppingCart cart = wrapper.getShoppingCart();

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
        ShoppingCart cart = wrapper.getShoppingCart();
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

    private void updateCounterTextField() {
        productCounterTextField.clear();
        productCounterTextField.setText( ((int) shoppingItem.getAmount()) + "");
    }

    public ProductDescriptionLightBox(Product product, IMatController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ProductDescription.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        closeImageView.onMouseClickedProperty().set(event -> onClick(event));
        productDescriptionTransparentPane.onMouseClickedProperty().set(event -> mainController.closeProductDescriptionLB());
        mainPane.onMouseClickedProperty().set(event -> mainController.mouseTrap(event));



    }
    @FXML
    void onClick(Event event) {
    mainController.closeProductDescriptionLB();
    }

    public void initializeProductCounterListener() {
        productCounterTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {
                    //focusgained - do nothing
                } else {
                    System.out.println(productCounterTextField.getText());
                    try {
                        int intValue = Integer.parseInt(productCounterTextField.getText());
                        if (intValue < 0) {
                            updateCounterTextField(); // Revert to old value.
                        } else {
                            if (intValue == 0) {
                                addProductAnchorPane.toFront();
                                shoppingItem.setAmount(0);
                                wrapper.getShoppingCart().removeItem(shoppingItem);
                                System.out.println(wrapper.getShoppingCart().getItems());
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
}
