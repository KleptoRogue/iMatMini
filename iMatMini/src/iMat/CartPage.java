package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;


import java.io.IOException;

public class CartPage extends AnchorPane {

    @FXML
    private Button keepShop;
    @FXML
    private Button emptyCart;
    @FXML
    private Button favoriteCart;


    @FXML
    private FlowPane productsPane;

    @FXML
    private Label xVaror;

    @FXML
    private Label xPris;

    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

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
        keepShop.addEventHandler(ActionEvent.ACTION, event -> mainController.openShop());
        emptyCart.addEventHandler(ActionEvent.ACTION, event -> removeAllGroceries());
        favoriteCart.addEventHandler(ActionEvent.ACTION, event -> favoriteAllGroceries());

        setGroceries();
    }

    private void setGroceries(){
        for(ShoppingItem grocery : wrapper.getShoppingCart().getItems()){
            productsPane.getChildren().add(new ProductItem(grocery.getProduct(), mainController));
        }

        xVaror.setText(wrapper.getShoppingCart().getItems().size()+ " st") ;
        xPris.setText(wrapper.getShoppingCart().getTotal()+ " kr") ;
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



}
