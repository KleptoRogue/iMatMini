package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FavoritePane extends Pane implements ChangedOnLogin{

    boolean added = false;
    @FXML
    Button favoriteButton;

    ShopPage shopPage;

    public FavoritePane(ShopPage shopPage) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/FavoritePane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.shopPage = shopPage;

        favoriteButton.onMouseClickedProperty().set(event -> shopPage.openFavorites());

    }

    @Override
    public void updateOnLogin() {
       if (!added){shopPage.categoryListFlowPane.getChildren().add(1, this);
      added = false;}



    }
}