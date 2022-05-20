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
    }

    @FXML
    private void login(){
        if(mainController.getUsers().size() == 0) error.setText("Ingen användare är registrerad!");
        for(User user : mainController.getUsers()){
             if(confirmLogin(user)) loginUser(user);
             else if(!mail.getText().equals(user.getUserName())) error.setText("Fel namn!");
             else if(!password.getText().equals(user.getPassword())) error.setText("Fel lösenord!");
             else  error.setText("Nu har nått gått sönder :(");
        }
    }

    private boolean confirmLogin(User user){
        return mail.getText().equals(user.getUserName()) && password.getText().equals(user.getPassword());
    }

    private void loginUser(User user){
        mainController.setLoggedinUser(user);
    }
}
