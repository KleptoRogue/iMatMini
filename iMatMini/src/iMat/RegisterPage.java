package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.User;


import java.io.IOException;

public class RegisterPage extends AnchorPane {

    @FXML
    private AnchorPane confirmReg;
    @FXML
    private AnchorPane confirmPassword;
    @FXML
    private AnchorPane confirmAddress;
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

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phone;
    @FXML
    private TextField mail;

    @FXML
    private TextField address;
    @FXML
    private TextField postcode;
  //  @FXML
  //  private TextField location;
    @FXML
    private TextField apartmentnumber;

    @FXML
    private TextField password;
    @FXML
    private TextField passwordConfirm;

    @FXML
    private Label error;

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
        p3NextStep.addEventHandler(ActionEvent.ACTION, event -> finishRegister());
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
        confirmAddress.toFront();
    }

    @FXML
    public void openWizard3(){
        confirmPassword.toFront();
    }

    @FXML
    public void finishRegister(){
        if(isValid()){
            createUser();
            createCustomer();
            wrapper.setIsLoogedIn(true);
            mainController.updateLogin();
            confirmReg.toFront();
        } else{
            error.setText(generateErrorMessage());
        }
    }

    private void createUser(){
        wrapper.setUser(mail.getText(), password.getText());
    }

    private void createCustomer(){
        wrapper.setCustomer(
                firstname.getText(),
                lastname.getText(),
                address.getText(),
                mail.getText(),
                phone.getText(),
                postcode.getText()
        );
    }

    private String generateErrorMessage(){
        if(!areFieldsFilled())        return "Hoppsan! Du måste Fylla i din mail!";
        else if(!isPasswordCorrect()) return "Hoppsan! Lösenorden måste vara lika!";
        else                          return "nått okänt gick galet :(";
    }

    private boolean isValid(){
        return areFieldsFilled() && isPasswordCorrect();
    }

    private boolean areFieldsFilled(){
        return !mail.getText().equals("") &&
                !password.getText().equals("");
    }

    private boolean isPasswordCorrect(){
        return password.getText().equals(passwordConfirm.getText());
    }


}
