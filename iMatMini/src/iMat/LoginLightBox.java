package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.User;


import java.io.IOException;

public class LoginLightBox extends AnchorPane {

    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private TextField mail;
    @FXML
    private TextField password;
    @FXML
    private Label error;
    @FXML
    private AnchorPane transparentPane;
    @FXML
    private AnchorPane loginPane;


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
        login.addEventHandler(ActionEvent.ACTION, event -> login());
        transparentPane.onMouseClickedProperty().set(event -> mainController.closeLoginLightBox());
        loginPane.onMouseClickedProperty().set(event -> mainController.mouseTrap(event));
    }

     @FXML
   private void login(){
        if(checkCredentials(wrapper.getUser())){
            wrapper.setIsLoogedIn(true);
            mainController.updateLogin();
            mainController.closeLoginLightBox();
            return;
        }

        error.setText(generateErrorMessage());
    }

    private String generateErrorMessage(){
        if(wrapper.getUser().getUserName().equals("")) return "Ingen användare är registrerad!";
        else if(!wrapper.getUser().getUserName().equals(mail.getText())) return "Fel mail!";
        else if(!wrapper.getUser().getPassword().equals(password.getText())) return "Fel lösenord!";
        else return "något oväntat gick fel :(";
    }

    private boolean checkCredentials(User user){
        return mail.getText().equals(user.getUserName()) &&
                password.getText().equals(user.getPassword()) &&
                !wrapper.getUser().getUserName().equals("");
    }
}
