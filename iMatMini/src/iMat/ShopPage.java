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
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;


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

    @FXML
    private Pane buttonsPane;
    @FXML
    private Pane sidepane;

    @FXML
    private Label currentPageLabel;
    @FXML
    private Button next;
    @FXML
    private Button prev;

    private int page = 1;
    private final int N_GROCERIES_PER_PAGE = 16;


    private IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();
    private IMatController mainController;

    private Map<Integer, ProductItem> productItemHashMap;
    private Map<Integer, ShoppingItem> cartShoppingItemHashMap;


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

        next.onMouseClickedProperty().set((event -> goNextPage(wrapper.getProducts())));
        prev.onMouseClickedProperty().set((event -> goPrevPage(wrapper.getProducts())));
        generateButtons(wrapper.getProducts(), 10);
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
        page = 1;

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
        updateProductList(productFlowPane, categorySearch);
        categoryLabel.setText("Sökning: " + search);
        categoryHeader.toFront();
        generateButtons(categorySearch, calcPages(categorySearch.size(), N_GROCERIES_PER_PAGE));
    }

    private int getSidePaneY(int nProducts){
        if (nProducts > N_GROCERIES_PER_PAGE) nProducts = N_GROCERIES_PER_PAGE;
        int itemsPerRow = 4;
        int rows = nProducts/itemsPerRow;
        int sidePaneH = 100;
        int itemH = 290;
        if(nProducts % itemsPerRow ==  0) return sidePaneH+itemH*(rows);
        else return sidePaneH+itemH*(rows+1);
    }

    private int calcPages(int a, int b){
        if(a == 0){
            return 0;
        }
        else {
            int times = 0;
            int copyB = b;
            while(b < a){
                b+=copyB;
                times++;
            }
            return times+1;
        }
    }

    private void updateProductList(FlowPane flowPane, List<Product> products) {
        flowPane.getChildren().clear();

        sidepane.setLayoutY(getSidePaneY(products.size()));

            //only one page
        if(calcPages(products.size(), N_GROCERIES_PER_PAGE) <= 1) {
            for (int i = 0; i < products.size(); i++) {
                flowPane.getChildren().add(new ProductItem(products.get(i), mainController));
            }
            //many pages
        } else{
                //last page
            if(products.size() < N_GROCERIES_PER_PAGE*page) {
                for (int i = N_GROCERIES_PER_PAGE * (page - 1); i < products.size(); i++) {
                    flowPane.getChildren().add(new ProductItem(products.get(i), mainController));
                }
                //more pages
            } else{
                for (int i = N_GROCERIES_PER_PAGE * (page - 1); i < N_GROCERIES_PER_PAGE * page; i++) {
                    flowPane.getChildren().add(new ProductItem(products.get(i), mainController));
                }
            }
        }

        next.onMouseClickedProperty().set((event -> goNextPage(products)));
        prev.onMouseClickedProperty().set((event -> goPrevPage(products)));
        currentPageLabel.setText("Sida: "+page+"/"+calcPages(products.size(), N_GROCERIES_PER_PAGE));
    }

    private void generateButtons(List<Product> products, int pages){
        buttonsPane.getChildren().clear();
        for (int i = 1; i < pages+1; i++){
            Button newButton = new Button(""+i);
            buttonsPane.getChildren().add(newButton);
            int finalI = i;
            newButton.onMouseClickedProperty().set((event -> goToPage(products, finalI)));
            newButton.getStylesheets().add(getClass().getResource("css/shoppage.css").toExternalForm());
        }
    }

    private void goToPage(List<Product> products, int p){
        page = p;
        updateProductList(productFlowPane, products);
    }

    private void goNextPage(List<Product> products){
        page++;
        updateProductList(productFlowPane, products);
    }

    private void goPrevPage(List<Product> products){
        page--;
        updateProductList(productFlowPane, products);
    }

    public void openCategory(String name, ProductCategory productCategory) {
        page = 1;
        productFlowPane.getChildren().clear();
        updateProductList(productFlowPane, wrapper.getProducts(productCategory));
        categoryLabel.setText(name);
        categoryHeader.toFront();
        generateButtons(wrapper.getProducts(productCategory), calcPages(wrapper.getProducts(productCategory).size(), N_GROCERIES_PER_PAGE));
    }


    public void openSweets() {
        productFlowPane.getChildren().clear();
        updateProductList(productFlowPane, wrapper.getProducts(ProductCategory.SWEET));
        categoryLabel.setText("Sötsaker");
        categoryHeader.toFront();
    }

    public void openFavorites() {
        productFlowPane.getChildren().clear();
        updateProductList(productFlowPane, wrapper.getFavorites());
        categoryLabel.setText("Favoriter");
        categoryHeader.toFront();
    }
}
