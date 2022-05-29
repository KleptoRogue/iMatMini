package iMat;

import java.net.URL;
import java.util.*;
import java.time.LocalDate;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;
import se.chalmers.cse.dat216.project.User;


public class IMatController implements Initializable {

    private final IMatDataHandlerWrapper model = IMatDataHandlerWrapper.getInstance();

    private ArrayList<ChangedOnLogin> changedOnLogin = new ArrayList<>();


    @FXML
    private AnchorPane accountPaneFXML;
    @FXML
    private AnchorPane headerPaneFXML;
    @FXML
    private AnchorPane cartPaneFXML;
    @FXML
    private AnchorPane startPaneFXML;
    @FXML
    private AnchorPane registerPaneFXML;
    @FXML
    private AnchorPane loginLightBoxFXML;
    @FXML
    private AnchorPane productDescriptionLightBoxFXML;

    private Map<Integer, ProductItem> productItemHashMap= initializeProductItemMap();

    @FXML AnchorPane checkoutWizardPane;// added for checkoutWizard

    @FXML
    FavoritePane favoritePane;

    @FXML ShopPage shopPage;
    @FXML RegisterPage registerPage;


    AccountPage accountPage; // tillagd för checkoutWizard

    public void openLoginLightBox(){
        loginLightBoxFXML.toFront();
    }
    public void closeLoginLightBox() {
        loginLightBoxFXML.toBack();
    }

    public void setPD (ProductDescriptionLightBox pd) {
        productDescriptionLightBoxFXML.getChildren().clear();
        productDescriptionLightBoxFXML.getChildren().add(pd);
    }

    public void openProductDescriptionLB(){
        productDescriptionLightBoxFXML.toFront();

    }


    public void closeProductDescriptionLB(){ productDescriptionLightBoxFXML.toBack();}

    public void openShop() {
        startPaneFXML.toFront();

        startPaneFXML.getChildren().clear();
        startPaneFXML.getChildren().add(new ShopPage(this));
        onHover();
    }
    public void openCart() {
        cartPaneFXML.toFront();

        cartPaneFXML.getChildren().clear();
        cartPaneFXML.getChildren().add(new CartPage(this));
    }
    public void openAccount() { accountPaneFXML.toFront();}

    public void openRegister() {
        loginLightBoxFXML.toBack();
        registerPaneFXML.toFront();

        registerPaneFXML.getChildren().clear();
        registerPaneFXML.getChildren().add(new RegisterPage(this));
        onHover();
    }

    public void openCheckout() {  // added for checkoutWizard
        checkoutWizardPane.toFront();}// added for checkoutWizard

    public void openOrderPage(){
        openAccount();
        accountPage.openOrderhistorik();
    }
    public void accountOrderToFront() {

    }

    List<Node>colorOnHoverList = new ArrayList<>();
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //model.reset();
        accountPage = new AccountPage(this); // för checkOutWizard
        shopPage = new ShopPage(this); //favorite
        registerPage = new RegisterPage(this); //favorite
        favoritePane = new FavoritePane(shopPage);  // favorite
        startPaneFXML.getChildren().add(shopPage);
        checkoutWizardPane.getChildren().add(new checkoutWizard(this));
        loginLightBoxFXML.getChildren().add(new LoginLightBox(this));
        headerPaneFXML.getChildren().add(new Header(this));
        cartPaneFXML.getChildren().add(new CartPage(this));
        registerPaneFXML.getChildren().add(registerPage);
        accountPaneFXML.getChildren().add(accountPage);
        addChangedOnLogin(favoritePane);

        onHover();

    }


    public void addChangedOnLogin(ChangedOnLogin willBeChanged){
        changedOnLogin.add(willBeChanged);
    }


    public void updateLogin(){
        for (ChangedOnLogin changed : changedOnLogin) {
            changed.updateOnLogin();
        }
    }

    public void mouseTrap(Event event) {
        event.consume();
    }

    public AnchorPane getAccountPaneFXML() {  // för checkOutWizard
        return accountPaneFXML;
    }



    public Map<Integer,ProductItem> getProductItemHashMap() {
        return productItemHashMap;
    }



    private Map<Integer, ProductItem> initializeProductItemMap() {
        Map<Integer, ProductItem> hashmap = new HashMap<>();
        Map<Integer, ShoppingItem> cartShoppingItemMap = initializeCartShoppingItemMap();
        List<Product> products = model.getProducts();
        for (Product product: products) {
            if (cartShoppingItemMap.containsKey(product.getProductId())) {
                ShoppingItem item = cartShoppingItemMap.get(product.getProductId());
                hashmap.put(product.getProductId(), new ProductItem( item, this));
            } else {
                hashmap.put(product.getProductId(), new ProductItem(product, this));
            }
        }
        return hashmap;
    }

    private Map<Integer, ShoppingItem> initializeCartShoppingItemMap() {
        Map<Integer, ShoppingItem> hashMap = new HashMap<>();
        ShoppingCart cart = model.getShoppingCart();
        List<ShoppingItem> items = cart.getItems();

        for (ShoppingItem item : items ) {
            hashMap.put(item.getProduct().getProductId(), item);
        }
        return  hashMap;
    }

    public void addToHoverList(Node node) {
     colorOnHoverList.add(node);
    }

    public void resetCheckout() {
        checkoutWizardPane.getChildren().clear();
        checkoutWizardPane.getChildren().add(new checkoutWizard(this));
        onHover();
    }
}
