package iMat;

import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;


import java.io.IOException;
import java.net.URL;

public class ShopPage extends AnchorPane{

    @FXML
    private FlowPane productFlowPane;
    @FXML
    private AnchorPane productItemAnchorPane;
    @FXML
    private AnchorPane shopStartPane;
    @FXML
    private AnchorPane shopCategoryPane;
    @FXML
    private FlowPane categoryFlowPane;
    @FXML
    private Label categoryLabel;

    @FXML Pane categoryHeader;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    Pane categoryPane;





    @FXML
    TitledPane sweetsPane;
    @FXML Button breadButton;
    @FXML Button beanButton;
    @FXML Button berryButton;
    @FXML Button citrusButton;
    @FXML Button hotDrinkButton;
    @FXML Button coldDrinkButton;
    @FXML Button exoticFruitButton;
    @FXML Button fishButton;
    @FXML Button vegFruitButton;
    @FXML Button cabbageButton;
    @FXML Button meatButton;
    @FXML Button dairyButton;
    @FXML Button melonButton;
    @FXML Button flourSugarSaltButton;
    @FXML Button nutsAndSeedsButton;
    @FXML Button pastaButton;
    @FXML Button potatoAndRiceButton;
    @FXML Button rootVegButton;
    @FXML Button fruitButton;
    @FXML Button sweetButton;
    @FXML Button herbButton;
    @FXML
    FlowPane categoryListFlowPane;


    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    Map<Integer, ProductItem> productItemHashMap = mainController.getProductItemMap();


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

        //productItemAnchorPane.onMouseClickedProperty().set(event -> mainController.openProductDescription());

       sweetsPane.onMouseClickedProperty().set(event -> openCategory("Sötsaker", ProductCategory.SWEET));
       breadButton.onMouseClickedProperty().set(event -> openCategory("Bröd", ProductCategory.BREAD));
       beanButton.onMouseClickedProperty().set(event -> openCategory("Bönor", ProductCategory.POD));
       berryButton.onMouseClickedProperty().set(event -> openCategory("Bär", ProductCategory.BERRY));
       citrusButton.onMouseClickedProperty().set(event -> openCategory("Citrusfrukter", ProductCategory.CITRUS_FRUIT));
       hotDrinkButton.onMouseClickedProperty().set(event -> openCategory("Varma Drycker", ProductCategory.HOT_DRINKS));
       coldDrinkButton.onMouseClickedProperty().set(event -> openCategory("Kalla Drycker", ProductCategory.COLD_DRINKS));
       exoticFruitButton.onMouseClickedProperty().set(event -> openCategory("Exotiska frukter", ProductCategory.EXOTIC_FRUIT));
       fishButton.onMouseClickedProperty().set(event -> openCategory("Fisk", ProductCategory.FISH));
       vegFruitButton.onMouseClickedProperty().set(event -> openCategory("Grönsaksfrukter", ProductCategory.VEGETABLE_FRUIT));
       cabbageButton.onMouseClickedProperty().set(event -> openCategory("Kål", ProductCategory.CABBAGE));
       meatButton.onMouseClickedProperty().set(event -> openCategory("Nötkött", ProductCategory.MEAT));
       dairyButton.onMouseClickedProperty().set(event -> openCategory("Mejeri", ProductCategory.DAIRIES));
       melonButton.onMouseClickedProperty().set(event -> openCategory("Melon", ProductCategory.MELONS));
       flourSugarSaltButton.onMouseClickedProperty().set(event -> openCategory("Mjöl, socker och salt", ProductCategory.FLOUR_SUGAR_SALT));
       nutsAndSeedsButton.onMouseClickedProperty().set(event -> openCategory("Nötter och frön", ProductCategory.NUTS_AND_SEEDS));
       pastaButton.onMouseClickedProperty().set(event -> openCategory("Pasta", ProductCategory.PASTA));
       potatoAndRiceButton.onMouseClickedProperty().set(event -> openCategory("Potatis och ris", ProductCategory.POTATO_RICE));
       rootVegButton.onMouseClickedProperty().set(event -> openCategory("Rotfrukter", ProductCategory.ROOT_VEGETABLE));
       fruitButton.onMouseClickedProperty().set(event -> openCategory("Övrig frukt", ProductCategory.FRUIT));
       sweetButton.onMouseClickedProperty().set(event -> openCategory("Sötsaker", ProductCategory.SWEET));
       herbButton.onMouseClickedProperty().set(event -> openCategory("Örter", ProductCategory.HERB));
       updateProductList(productFlowPane, wrapper.getProducts());

       searchButton.onMouseClickedProperty().set((event -> doSearch()));



       searchButton.onMouseClickedProperty().set((event -> doSearch()));
    }

    public void resizeCategoryLabel(String string){
        int fontSize = 18; //px
        int size = string.length() + 12;
        int sizeComp = size + 9;
        double tmp =size*fontSize;   
            categoryPane.setPrefWidth(tmp);
        System.out.println((categoryHeader.getWidth()/2) - (tmp/2));
         double paneX = ((categoryHeader.getWidth()/2) - (tmp/2));
          
           categoryLabel.setPrefWidth(size*fontSize);
           //categoryLabel.setTranslateX(-tmp/2);
           categoryPane.setLayoutX(paneX);
           
            }


    private void doSearch(){
        String search = searchTextField.getText();
        resizeCategoryLabel(search);
        //ProductCategory searchEnum = ProductCategory.valueOf(search);
        List<Product> categorySearch= new ArrayList<>();//wrapper.getProducts(searchEnum);
        List <Product> productSearch = wrapper.findProducts(search);


        for(Product p : productSearch) {
            if(!categorySearch.contains(p)) {
                categorySearch.add(p);
            }}

        productFlowPane.getChildren().clear();
        updateProductList(categoryFlowPane, categorySearch);
        categoryLabel.setText("Sökning: " + search);
        shopCategoryPane.toFront();


    }

    private void updateProductList(FlowPane flowPane, List<Product> products) {
        flowPane.getChildren().clear();
        for (Product product : products) {
            flowPane.getChildren().add(productItemHashMap.get(product.getProductId()));
        }
    }





    public void openCategory(String name, ProductCategory productCategory) {
        productFlowPane.getChildren().clear();
        updateProductList(categoryFlowPane, wrapper.getProducts(productCategory));
        categoryLabel.setText(name);
        shopCategoryPane.toFront();
    }



    public void openSweets() {
        productFlowPane.getChildren().clear();
        updateProductList(categoryFlowPane, wrapper.getProducts(ProductCategory.SWEET));
        categoryLabel.setText("Sötsaker");
        shopCategoryPane.toFront();
    }

    public void openFavorites() {
        productFlowPane.getChildren().clear();
        updateProductList(categoryFlowPane, wrapper.getFavorites());
        categoryLabel.setText("Favoriter");
        shopCategoryPane.toFront();
    }



}
