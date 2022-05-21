package iMat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingCart;
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

    public void openLoginLightBox(){
        loginLightBoxFXML.toFront();
    }
    public void closeLoginLightBox() {
        loginLightBoxFXML.toBack();
    }


    public void openShop() {
        startPaneFXML.toFront();
    }

    public void openCart() {
        cartPaneFXML.toFront();
    }

    public void openRegister() {
        loginLightBoxFXML.toBack();
        registerPaneFXML.toFront();
    }

    public void openAccount() {
        accountPaneFXML.toFront();
    }


    public void openProductDescription() {
        productDescriptionLightBoxFXML.toFront();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startPaneFXML.getChildren().add(new ShopPage(this));

        loginLightBoxFXML.getChildren().add(new LoginLightBox(this));
        headerPaneFXML.getChildren().add(new Header(this));
        cartPaneFXML.getChildren().add(new CartPage(this));
        registerPaneFXML.getChildren().add(new RegisterPage(this));
        accountPaneFXML.getChildren().add(new AccountPage(this));
    }


    public void addChangedOnLogin(ChangedOnLogin willBeChanged){
        changedOnLogin.add(willBeChanged);
    }


    public void updateLogin(){
        for (ChangedOnLogin changed : changedOnLogin) {
            changed.updateOnLogin();
        }
    }

    public void mouseTrap(MouseEvent mouseEvent) {
        //att göra
    }



}
