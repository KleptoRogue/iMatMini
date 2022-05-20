package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class Header extends AnchorPane {

    @FXML
    private AnchorPane homeAnchorPane;
    @FXML
    private AnchorPane cartAnchorPane;
    @FXML
    private AnchorPane loginAnchorPane;

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


        homeAnchorPane.onMouseClickedProperty().set(event -> mainController.openShop());
        cartAnchorPane.onMouseClickedProperty().set(event -> mainController.openCart());
        loginAnchorPane.onMouseClickedProperty().set(event -> mainController.openLoginLightBox());
    }
}
