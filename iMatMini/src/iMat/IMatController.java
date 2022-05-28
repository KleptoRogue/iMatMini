package iMat;

import java.net.URL;
import java.util.*;
import java.time.LocalDate;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.User;


public class IMatController implements Initializable {

    private final IMatDataHandlerWrapper model = IMatDataHandlerWrapper.getInstance();

    private ArrayList<ChangedOnLogin> changedOnLogin = new ArrayList<>();

    // Hashmap: Key: ProductID. Note product item contains shoppingItem.
    private final Map<Integer, ProductItem> productItemMap = initializeProductItemMap();


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

    public void openProductDescriptionLB(){ productDescriptionLightBoxFXML.toFront();}
    public void closeProductDescriptionLB(){ productDescriptionLightBoxFXML.toBack();}

    public void openShop() { startPaneFXML.toFront();}
    public void openCart() {
        cartPaneFXML.toFront();
    }
    public void openAccount() { accountPaneFXML.toFront();}

    public void openRegister() {
        loginLightBoxFXML.toBack();
        registerPaneFXML.toFront();

        //Nollställ registreringsprocessen när man öppnar den
        //Detta för att förhindra desyncs ifall följande:
        //register user --> account page --> logout --> homepage --> register --> accountpage
        //användaren når då accountpage utloggad
        registerPaneFXML.getChildren().clear();
        registerPaneFXML.getChildren().add(new RegisterPage(this));
    }

    public void openCheckout() {  // added for checkoutWizard
        checkoutWizardPane.toFront();}// added for checkoutWizard

    public void openOrderPage(){
        openAccount();
        accountPage.openOrderhistorik();
    }
    public void accountOrderToFront() {

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // model.reset();
        accountPage = new AccountPage(this); // för checkOutWizard
        shopPage = new ShopPage(this); //favorite
        registerPage = new RegisterPage(this); //favorite
        favoritePane = new FavoritePane(shopPage);  // favorite
        startPaneFXML.getChildren().add(shopPage);
        checkoutWizardPane.getChildren().add(new checkoutWizard(this));

        headerPaneFXML.getChildren().add(new Header(this));
        cartPaneFXML.getChildren().add(new CartPage(this));
        registerPaneFXML.getChildren().add(registerPage);
        accountPaneFXML.getChildren().add(accountPage);
        addChangedOnLogin(favoritePane);
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

    public Map<Integer, ProductItem> getProductItemMap() {
        return productItemMap;
    }

    private Map<Integer, ProductItem> initializeProductItemMap() {
        Map<Integer, ProductItem> hashmap = new HashMap<>();
        List<Product> products = model.getProducts();
        for (Product product: products) {
            hashmap.put(product.getProductId(), new ProductItem(product, this));
        }
        return hashmap;
    }


}
