package iMat;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import javax.swing.text.html.ImageView;

public class IMatController implements Initializable {

    private final IMatDataHandlerWrapper  controller = IMatDataHandlerWrapper.getInstance();

    // Needed for listeners when clicking on product buttons
    @FXML
    private Button addProductButton;

    @FXML
    private AnchorPane addRemoveProductAnchorPane; // -[int] +

    @FXML
    private AnchorPane addProductAnchorPane;     // To switch which anchorPane to show

    @FXML
    private ImageView starImageView;

    @FXML
    private TextField productCounterTextField;

    private final Map<String, Product> productItemMap = new HashMap<>();


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
