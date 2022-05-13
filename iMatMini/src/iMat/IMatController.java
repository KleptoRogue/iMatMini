package iMat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class IMatController implements Initializable {

    //new
    @FXML
    private AnchorPane loaderPane;



    public void loadWizard (ActionEvent event) throws IOException {
        AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource("RegisterWizard.fxml"));
        loaderPane.getChildren().add(newLoadedPane);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
