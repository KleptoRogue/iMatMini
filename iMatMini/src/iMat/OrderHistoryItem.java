package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class OrderHistoryItem extends AnchorPane {

    Order order;

    @FXML
    Label orderNumberLabel;
    @FXML Label orderDateLabel;
    @FXML Label orderCostLabel;
    @FXML
    FlowPane orderedItemsFlowPane;


    public OrderHistoryItem(Order order) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/OrderHistoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;
        orderDateLabel.setText(order.getDate().toString());
        orderNumberLabel.setText(String.valueOf(order.getOrderNumber()));
        orderCostLabel.setText(getPrice() + " kr");
        populateFlowPane();



    }

    private double getPrice (){
        double tmp = 0;
       for(ShoppingItem item : order.getItems()){
           tmp += item.getTotal();
       }
       return Math.round(tmp);}

    private void populateFlowPane() {
        for(ShoppingItem item : order.getItems()){
           orderedItemsFlowPane.getChildren().add(new Text(item.getProduct().toString().split(" - ", 2)[1]
                   + ".    " + item.getAmount() + " st.    " + Math.round( item.getTotal()) + " kr."));
        }
    }

}
