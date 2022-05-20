package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;

public class ShopPage extends AnchorPane {

    @FXML
    private FlowPane productFlowPane;
    @FXML
    private AnchorPane productPanelAnchorPane;



    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    public ShopPage(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ShopPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainController = mainController;
        //productPanelAnchorPane.onMouseClickedProperty().set(event -> mainController.openProductDescription());


        updateProductList(wrapper.getProducts());

    }

    private void updateProductList(List<Product> products) {
        productFlowPane.getChildren().clear();
        for (Product product : products) {
            productFlowPane.getChildren().add(new ProductItem(product, mainController));
        }
    }

}
