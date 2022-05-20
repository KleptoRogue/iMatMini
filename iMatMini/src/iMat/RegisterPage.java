package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class RegisterPage extends AnchorPane {

    @FXML
    private AnchorPane confirmReg;
    @FXML
    private AnchorPane confirmPassword;
    @FXML
    private AnchorPane confirmAdress;
    @FXML
    private AnchorPane confirmPerson;

    @FXML
    private Button p1KeepShop;
    @FXML
    private Button p1NextStep;
    @FXML
    private Button p2KeepShop;
    @FXML
    private Button p2NextStep;
    @FXML
    private Button p2GoBack;
    @FXML
    private Button p3KeepShop;
    @FXML
    private Button p3NextStep;
    @FXML
    private Button p3GoBack;
    @FXML
    private Button p4KeepShop;
    @FXML
    private Button goToAccount;
    @FXML
    private Button goToShop;

    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    public RegisterPage(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/RegisterPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainController = mainController;

        p1KeepShop.addEventHandler(ActionEvent.ACTION, event -> mainController.openShop());
        p1NextStep.addEventHandler(ActionEvent.ACTION, event -> openWizard2());

        p2KeepShop.addEventHandler(ActionEvent.ACTION, event -> mainController.openShop());
        p2NextStep.addEventHandler(ActionEvent.ACTION, event -> openWizard3());
        p2GoBack.addEventHandler(ActionEvent.ACTION, event -> openWizard1());

        p3KeepShop.addEventHandler(ActionEvent.ACTION, event -> mainController.openShop());
        p3NextStep.addEventHandler(ActionEvent.ACTION, event -> openWizard4());
        p3GoBack.addEventHandler(ActionEvent.ACTION, event -> openWizard2());

        p4KeepShop.addEventHandler(ActionEvent.ACTION, event -> mainController.openShop());
        goToAccount.addEventHandler(ActionEvent.ACTION, event -> mainController.openAccount());
        goToShop.addEventHandler(ActionEvent.ACTION, event -> mainController.openShop());
    }

    @FXML
    public void openWizard1(){
        confirmPerson.toFront();
    }

    @FXML
    public void openWizard2(){
        confirmAdress.toFront();
    }

    @FXML
    public void openWizard3(){
        confirmPassword.toFront();
    }

    @FXML
    public void openWizard4(){
        confirmReg.toFront();
    }
}
