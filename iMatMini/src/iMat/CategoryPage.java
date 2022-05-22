package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;

public class CategoryPage extends AnchorPane {
    @FXML
    Text categoryHeader;

    private IMatController mainController;

    @FXML
    FlowPane categoryFlowPane;

    @FXML
    TitledPane sweetsPane;

    public CategoryPage (IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CategoryPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainController = mainController;

    }


    private void updateProductList(List<Product> products) {
        categoryFlowPane.getChildren().clear();
        for (Product product : products) {
            categoryFlowPane.getChildren().add(new ProductItem(product, mainController));
        }




    }









}
