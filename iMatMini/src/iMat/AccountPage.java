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
    private Button favoriterButton;
    @FXML
    private Button användaruppgifter;
    @FXML
    private Button loggaUtButton;
    @FXML
    private AnchorPane orderhistorikPane;
    @FXML
    private AnchorPane favoriterPane;
    @FXML
    private AnchorPane startPaneFXML;

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
        favoriterButton.addEventHandler(ActionEvent.ACTION, event -> openFavoriter());
        användaruppgifter.addEventHandler(ActionEvent.ACTION, event -> openAnvändaruppgifter());
        loggaUtButton.addEventHandler(ActionEvent.ACTION, event -> loggaUt());

    }

    public void openOrderhistorik() {
        orderhistorikPane.toFront();
    }

    public  void openFavoriter(){
        favoriterPane.toFront();
    }

    public void openAnvändaruppgifter() {
        orderhistorikPane.toBack();
        favoriterPane.toBack();
    }

    private void loggaUt() {
        wrapper.setIsLoogedIn(false);
        mainController.updateLogin();
        //open home page
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
