package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartPage extends AnchorPane {
//
    @FXML
    private Button keepShop;
    @FXML
    private Button emptyCart;
    @FXML
    private Button favoriteCart;
    @FXML Button goToCheckoutButton;


    @FXML
    private FlowPane productsPane;

    @FXML
    private Label xVaror;

    @FXML
    private Label xPris;

    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    List<Node> colorOnHoverList = new ArrayList<>();
    public ColorAdjust brighterColor = new ColorAdjust(0, 0, 0.3,0);
    public ColorAdjust normalColor = new ColorAdjust(0, 0, 0,0);
    public ColorAdjust darkerColor = new ColorAdjust(0, 0, -0.3,0);
    void onHover(){
        for (Node node : colorOnHoverList){
            node.onMouseEnteredProperty().set(event -> node.setEffect(brighterColor));
            node.onMouseExitedProperty().set(event -> node.setEffect(normalColor));
            node.onMousePressedProperty().set(event -> node.setEffect(darkerColor));
            node.onMouseReleasedProperty().set(event -> node.setEffect(normalColor));
        }
    }

    public CartPage(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CartPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;
        addToHoverList(keepShop);
        addToHoverList(emptyCart);
        addToHoverList(favoriteCart);
        addToHoverList(goToCheckoutButton);
        keepShop.addEventHandler(ActionEvent.ACTION, event -> mainController.openShop());
        emptyCart.addEventHandler(ActionEvent.ACTION, event -> removeAllGroceries());
        favoriteCart.addEventHandler(ActionEvent.ACTION, event -> favoriteAllGroceries());
        goToCheckoutButton.addEventHandler(ActionEvent.ACTION, event -> mainController.openCheckout());
        setGroceries();
        onHover();
    }

    private void setGroceries(){
        for(ShoppingItem grocery : wrapper.getShoppingCart().getItems()){
            Map<Integer, ProductItem> productMap = mainController.getProductItemHashMap();
            productsPane.getChildren().add(productMap.get(grocery.getProduct().getProductId()));
        }

        xVaror.setText(getSumOfProductsInCart()+ " st") ;
        xPris.setText(wrapper.getShoppingCart().getTotal()+ " kr") ;
    }

    private int getSumOfProductsInCart() {
        int sumOfItems = 0;
        List<ShoppingItem> shoppingItems = wrapper.getShoppingCart().getItems();
        for (ShoppingItem item : shoppingItems) {
            sumOfItems += item.getAmount();
        }
        return sumOfItems;
    }

    private void removeAllGroceries(){
        wrapper.getShoppingCart().clear();
        productsPane.getChildren().clear();
        xVaror.setText(wrapper.getShoppingCart().getItems().size()+ " st") ;
        xPris.setText(wrapper.getShoppingCart().getTotal()+ " kr") ;
    }

    private void favoriteAllGroceries(){
        for(ShoppingItem shoppingItem : wrapper.getShoppingCart().getItems()){
            wrapper.addFavorite(shoppingItem.getProduct());
        }
        productsPane.getChildren().clear();
        setGroceries();
    }

    public void addToHoverList(Node node) {
        colorOnHoverList.add(node);
    }



}
