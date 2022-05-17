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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

public class IMatController implements Initializable {

    private final IMatDataHandlerWrapper model = IMatDataHandlerWrapper.getInstance();

    @FXML
    private FlowPane productFlowPane;

    private final Map<String, Product> productItemMap = new HashMap<>();

    private final Map<Product, Integer> favoritedProductMap = new HashMap<>();


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
    public void loadWizard (ActionEvent event) throws IOException {
        loaderPane.getChildren().remove(loaderPane.getChildren());

        AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource("RegisterWizard.fxml"));
        loaderPane.getChildren().add(newLoadedPane);
    }

    @FXML
    public void loadStart (ActionEvent event) throws IOException {
        loaderPane.getChildren().remove(loaderPane.getChildren());

        AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        loaderPane.getChildren().add(newLoadedPane);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeFavoritedProductMap(model.getFavorites());
        updateProductList(model.getProducts());
    }



    public Boolean isProductFavorited(Product product) {
        if (favoritedProductMap.get(product) == null)
            return false;

        return true;
    }

    private void initializeFavoritedProductMap(List<Product> favorites) {
        for (Product favorite : favorites) {
            favoritedProductMap.put(favorite, 1);
        }
    }
    private void updateProductList(List<Product> products) {
        productFlowPane.getChildren().clear();

        for (Product product : products) {
            productFlowPane.getChildren().add(new ProductListItem(product, this));
        }
    }


    protected void addFavorite(Product product, AnchorPane favorited) {
        model.addFavorite(product);
        System.out.println("Favorited: " + product);
        favorited.toFront();
    }

    protected void removeFavorite(Product product, AnchorPane unfavorited) {
        model.removeFavorite(product);
        System.out.println("Unfavorited: " + product);
        unfavorited.toFront();
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
}
