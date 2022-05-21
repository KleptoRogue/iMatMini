package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class Header extends AnchorPane implements ChangedOnLogin {

    @FXML
    private AnchorPane homeAnchorPane;
    @FXML
    private AnchorPane cartAnchorPane;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private AnchorPane profileAnchorPane;


    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    public Header(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Header.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;

        mainController.addChangedOnLogin(this);
        homeAnchorPane.onMouseClickedProperty().set(event -> mainController.openShop());
        cartAnchorPane.onMouseClickedProperty().set(event -> mainController.openCart());
        loginAnchorPane.onMouseClickedProperty().set(event -> mainController.openLoginLightBox());
        profileAnchorPane.onMouseClickedProperty().set(event -> mainController.openAccount());
    }


    @Override
    public void updateOnLogin() {
        if(wrapper.getIsLoggedIn()) profileAnchorPane.toFront();
        else loginAnchorPane.toFront();

    }
}
