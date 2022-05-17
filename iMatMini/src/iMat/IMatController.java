package iMat;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

import javax.swing.text.html.ImageView;

public class IMatController implements Initializable {

    private final IMatDataHandlerWrapper model = IMatDataHandlerWrapper.getInstance();

    // Needed for listeners when clicking on product buttons
    @FXML
    private Button addProductButton;


    @FXML
    private ImageView starImageView;

    @FXML
    private TextField productCounterTextField;

    @FXML
    private FlowPane productFlowPane;

    private final Map<String, Product> productItemMap = new HashMap<>();


    //new
    @FXML
    private AnchorPane loaderPane;
    @FXML
    private AnchorPane confirmReg;
    @FXML
    private AnchorPane confirmPassword;
    @FXML
    private AnchorPane confirmAdress;
    @FXML
    private AnchorPane confirmPerson;
    @FXML
    private AnchorPane loginpage;
    @FXML
    private AnchorPane mainpage;



    @FXML
    public void confirmRegToFront(){
        confirmReg.toFront();
    }

    @FXML
    public void confirmPasswordToFront(){
        confirmPassword.toFront();
    }

    @FXML
    public void confirmAdressToFront(){
        confirmAdress.toFront();
    }

    @FXML
    public void confirmPersonToFront(){
        confirmPerson.toFront();
    }

    @FXML
    public void loginToFront(){
        loginpage.toFront();
    }

    @FXML
    public void mainToFront(){
        mainpage.toFront();
    }

    @FXML
    public void loadWizard (ActionEvent event) throws IOException {
        loaderPane.getChildren().remove(loaderPane.getChildren());
        mainToFront();
        AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource("FXML/RegisterWizard.fxml"));
        loaderPane.getChildren().add(newLoadedPane);
    }

    @FXML
    public void loadStart (ActionEvent event) throws IOException {
        loaderPane.getChildren().remove(loaderPane.getChildren());

        AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource("FXML/StartPage.fxml"));
        loaderPane.getChildren().add(newLoadedPane);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //updateProductList(model.getProducts());
    }

    private void updateProductList(List<Product> products) {
        productFlowPane.getChildren().clear();
        for (Product product : products) {
            productFlowPane.getChildren().add(new ProductListItem(product, this));
        }
    }


    protected void addProduct(Product product, Node anchorpane) {
        //TODO Connect to backend to update cart and productCounterTextField
        anchorpane.toFront();
    }

    protected void removeProduct(Product product, Node anchorpane) {
        // TODO Connect to backend to update cart and productCounterTextField, switch anchorpane if 0
        anchorpane.toFront();
    }

    protected Image getFXImage(Product product, double width, double height) {
        return model.getFXImage(product, width, height);
    }

    public void mouseTrap(MouseEvent mouseEvent) {
        //att göra
    }

}
