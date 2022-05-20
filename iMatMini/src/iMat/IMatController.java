package iMat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class IMatController implements Initializable {

    private final IMatDataHandlerWrapper model = IMatDataHandlerWrapper.getInstance();


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

    public void openLoginLightBox(){
        loginLightBoxFXML.toFront();
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startPaneFXML.getChildren().add(new ShopPage(this));

        loginLightBoxFXML.getChildren().add(new LoginLightBox(this));
        headerPaneFXML.getChildren().add(new Header(this));
        cartPaneFXML.getChildren().add(new CartPage(this));
        registerPaneFXML.getChildren().add(new RegisterPage(this));
        accountPaneFXML.getChildren().add(new AccountPage(this));
    }



    public void mouseTrap(MouseEvent mouseEvent) {
        //att göra
    }

}
