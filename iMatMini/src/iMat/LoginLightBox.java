package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class LoginLightBox extends AnchorPane {

    @FXML
    private Button register;
    @FXML
    private Button login;

    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    public LoginLightBox(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/LoginLightBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;

        register.addEventHandler(ActionEvent.ACTION, event -> mainController.openRegister());

    }

}
