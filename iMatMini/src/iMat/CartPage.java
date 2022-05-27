package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class CartPage extends AnchorPane {

    @FXML Button goToCheckoutButton; // added for checkoutWizard

    @FXML
    private Button keepShop;

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
        goToCheckoutButton.addEventHandler(ActionEvent.ACTION, event -> mainController.openCheckout()); // added for checkoutWizard
    }

}
