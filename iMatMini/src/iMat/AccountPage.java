package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class AccountPage extends AnchorPane {

    @FXML
    private Button orderhistorik;
    @FXML
    private AnchorPane orderhistorikPane;

    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    public AccountPage(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/AccountPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;

        orderhistorik.addEventHandler(ActionEvent.ACTION, event -> openOrderhistorik());

    }

    public void openOrderhistorik() {
        orderhistorikPane.toFront();
    }

}
