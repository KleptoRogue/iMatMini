package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;

public class StartPage extends AnchorPane {

    @FXML
    private AnchorPane productFlowPane;

    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    public StartPage(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ShopPage.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        updateProductList(wrapper.getProducts());

        this.mainController = mainController;
    }

    private void updateProductList(List<Product> products) {
        for (Product product : products) {
            productFlowPane.getChildren().add(new ProductItem(product, mainController));
        }
    }

}
