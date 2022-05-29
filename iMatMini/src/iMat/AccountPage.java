package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AccountPage extends AnchorPane implements ChangedOnLogin{

    @FXML
    private Button orderhistorik;
    @FXML
    private Button favoriterButton;
    @FXML
    private Button användaruppgifter;
    @FXML
    private Button loggaUtButton;
    @FXML
    private AnchorPane orderhistorikPane;
    @FXML
    private AnchorPane favoriterPane;
    @FXML
    private AnchorPane startPaneFXML;

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField username;
    @FXML
    private TextField phone;
    @FXML
    private TextField password;
    @FXML
    private TextField address;
   // @FXML
  //  private TextField location;
    @FXML
    private TextField postcode;

    @FXML
    public FlowPane orderHistoryFlowPane;

    @FXML
    public FlowPane accountFavoritesFlowPane;


    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    private Map<Integer, ProductItem> productItemHashMap;

    public AccountPage(IMatController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/AccountPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;
        this.productItemHashMap = mainController.getProductItemHashMap();

        mainController.addChangedOnLogin(this);
        orderhistorik.addEventHandler(ActionEvent.ACTION, event -> openOrderhistorik());
        favoriterButton.addEventHandler(ActionEvent.ACTION, event -> openFavoriter());
        användaruppgifter.addEventHandler(ActionEvent.ACTION, event -> openAnvändaruppgifter());
        loggaUtButton.addEventHandler(ActionEvent.ACTION, event -> loggaUt());

    }

    public void openOrderhistorik() {
        orderHistoryFlowPane.getChildren().clear();
        orderhistorikPane.toFront();
        System.out.println("hellooooo");
        populateFlowPane();
        }

      private void populateFlowPane() {
          orderHistoryFlowPane.getChildren().clear();
        for (Order order : wrapper.getOrders()){
            orderHistoryFlowPane.getChildren().add(new OrderHistoryItem(order));
        }
      }



    public  void openFavoriter(){
        favoriterPane.toFront();
        accountFavoritesFlowPane.getChildren().clear();
        List<Product> favorites = wrapper.getFavorites();
        Map<Integer, ProductItem> productItemHashMap = mainController.getProductItemHashMap();

        for (Product favorite : favorites) {
            accountFavoritesFlowPane.getChildren().add(productItemHashMap.get(favorite.getProductId()));
        }
    }

    public void openAnvändaruppgifter() {
        orderhistorikPane.toBack();
        favoriterPane.toBack();
    }

    private void loggaUt() {
        wrapper.setIsLoogedIn(false);
        mainController.updateLogin();
        updateProductItems();
        //open home page
    }

    @Override
    public void updateOnLogin() {
        firstname.setText(wrapper.getCustomer().getFirstName());
        lastname.setText(wrapper.getCustomer().getLastName());
        username.setText(wrapper.getCustomer().getEmail());
        phone.setText(wrapper.getCustomer().getPhoneNumber());
        password.setText(wrapper.getUser().getPassword());
        address.setText(wrapper.getCustomer().getAddress());
        postcode.setText(wrapper.getCustomer().getPostCode());
        updateProductItems();
    }

    private void updateProductItems() {
        for (ProductItem item : productItemHashMap.values()) {
            item.updateProducts();
        }
    }
}
