package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class AccountPage extends AnchorPane implements ChangedOnLogin{

    @FXML
    private Button orderhistorik;
    @FXML
    private AnchorPane orderhistorikPane;

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField username;
    @FXML
    private TextField phone;
    @FXML
    private TextField password;
    @FXML
    private TextField address;
   // @FXML
  //  private TextField location;
    @FXML
    private TextField postcode;

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

        mainController.addChangedOnLogin(this);
        orderhistorik.addEventHandler(ActionEvent.ACTION, event -> openOrderhistorik());

    }

    public void openOrderhistorik() {
        orderhistorikPane.toFront();
    }

    @Override
    public void updateOnLogin() {
        firstname.setText(wrapper.getCustomer().getFirstName());
        lastname.setText(wrapper.getCustomer().getLastName());
        username.setText(wrapper.getCustomer().getEmail());
        phone.setText(wrapper.getCustomer().getPhoneNumber());
        password.setText(wrapper.getUser().getPassword());
        address.setText(wrapper.getCustomer().getAddress());
        postcode.setText(wrapper.getCustomer().getPostCode());

    }
}
