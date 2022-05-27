package iMat;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.w3c.dom.css.RGBColor;
import java.time.LocalDate;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class checkoutWizard extends AnchorPane implements Initializable {



    boolean onConfirmStep = false;

    IMatDataHandlerWrapper wrapper = IMatDataHandlerWrapper.getInstance();

    @FXML
    Button popUpConfirmationButton;
    @FXML
    Button popUpCancelButton;
    @FXML
    Button popUpExitButton;
    @FXML
    Circle adressCircle;
    @FXML
    Circle affirmationCircle;
    @FXML
    Circle deliveryCircle;
    @FXML
    Circle lastCheckCircle;
    @FXML
    Circle paymentCircle;

    @FXML
    RadioButton deliveryDateRadioButton1;
    @FXML
    RadioButton deliveryDateRadioButton2;
    @FXML
    RadioButton deliveryDateRadioButton3;
    @FXML
    RadioButton deliveryDateRadioButton4;
    @FXML
    RadioButton deliveryDateRadioButton5;
    @FXML
    RadioButton deliveryDateRadioButton6;
    @FXML
    RadioButton deliveryDateRadioButton7;
    @FXML
    RadioButton deliveryTimeRadioButton1;
    @FXML
    RadioButton deliveryTimeRadioButton2;
    @FXML
    RadioButton deliveryTimeRadioButton3;
    @FXML
    RadioButton deliveryTimeRadioButton4;
    @FXML
    RadioButton deliveryTimeRadioButton5;
    @FXML
    RadioButton deliveryTimeRadioButton6;
    @FXML
    RadioButton deliveryTimeRadioButton7;
    @FXML
    RadioButton eInvoiceRadioButton;
    @FXML
    RadioButton invoiceRadioButton;
    @FXML
    RadioButton paymentOptionCardRadioButton;
    @FXML
    RadioButton swishRadioButton;
    @FXML
    Rectangle adressRectangle;
    @FXML
    Rectangle affirmationRectangle;
    @FXML
    Rectangle deliveryRectangle;
    @FXML
    Rectangle lastCheckRectangle;
    @FXML
    Rectangle lightBoxPopUp;
    @FXML
    Text controlAdressText;
    @FXML
    Text controlApartmentNumberText;
    @FXML
    Text controlCardName;
    @FXML
    Text controlCardNumberText;
    @FXML
    Text controlChangeAdressText;
    @FXML
    Text controlChangeDeliveryText;
    @FXML
    Text controlChangePaymentText;
    @FXML
    Text controlCityText;
    @FXML
    Text controlDeliveryDateText;
    @FXML
    Text controlDeliveryInTextText;
    @FXML
    Text controlDeliveryTimeText;
    @FXML
    Text controlExpirationDateText;
    @FXML
    Text controlPostcodeText;
    @FXML
    Text paymentMethodText;
    @FXML Text controlCvcCode;
    @FXML
    Rectangle paymentRectangle;
    @FXML
    Text stepCounterText;
    @FXML
    Text stepNameText;
    @FXML
    TextField addressTextField;
    @FXML
    TextField apartmentNumberTextField;
    @FXML
    TextField cardNameTextField;
    @FXML
    TextField cardNumberTextField;
    @FXML
    TextField cityTextField;
    @FXML
    TextField cvcTextField;

    @FXML
    TextField expirationDateTextField;
    @FXML
    TextField postcodeTextField;
    @FXML
    ImageView deliveryBackImageView;
    @FXML
    ImageView adressNextImageView;
    @FXML
    ImageView controlBackImageView;
    @FXML
    ImageView deliveryNextImageView;
    @FXML
    ImageView keepShoppingImageView;
    @FXML
    ImageView seeOrderImageView;
    @FXML
    ImageView controlFinishOrderImageView;
    @FXML
    ImageView paymentNextImageView;
    @FXML
    ImageView paymentBackImageView;

    @FXML
    AnchorPane adressStep;
    @FXML
    AnchorPane checkupStep;
    @FXML
    AnchorPane confirmationStep;
    @FXML
    AnchorPane deliveryStep;
    @FXML
    AnchorPane paymentStep;
    @FXML AnchorPane popUpPane;

    IMatController mainController;


    Rectangle lastRectangle;
    Circle lastCircle;
    Color grey = Color.rgb(199,199,199, 1);
    Color green = Color.rgb(23, 238, 23, 1);

    private void setLastRectangleAndCircle(Rectangle rec, Circle cic){
        lastRectangle = rec;
        lastCircle = cic;

    }

    void setColor(Rectangle rec, Circle circle, Color color) {
        circle.setFill(color);
        rec.setFill(color);
    }

   public void changeCircleAndRec(Rectangle rec, Circle circle){
     setColor(rec, circle, green);
     setColor(lastRectangle, lastCircle, grey);
     setLastRectangleAndCircle(rec, circle);

   }

   public void changeStepText(String name, int number) {
        stepNameText.setText(name); stepCounterText.setText("Steg " + number + " av 5");
   }

   public checkoutWizard(IMatController mainController) {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/checkoutWizard.fxml"));
       fxmlLoader.setRoot(this);
       fxmlLoader.setController(this);

       try {
           fxmlLoader.load();
       } catch (IOException exception) {
           throw new RuntimeException(exception);
       }

       keepShoppingImageView.onMouseReleasedProperty().set(event -> mainController.openShop());
       adressNextImageView.onMouseClickedProperty().set(event -> goToDeliveryStep());
       deliveryNextImageView.onMouseClickedProperty().set(event -> goToPaymentStep());
       deliveryBackImageView.onMouseClickedProperty().set(event -> goToAdressStep());
       paymentNextImageView.onMouseClickedProperty().set(event -> goToCheckStep());
       paymentBackImageView.onMouseClickedProperty().set(event -> goToCheckStep());
       controlFinishOrderImageView.onMouseClickedProperty().set(event -> openPopUp());
       controlBackImageView.onMouseClickedProperty().set(event -> goToPaymentStep());

       popUpCancelButton.onMouseClickedProperty().set(event -> popUpPane.toBack());
       popUpExitButton.onMouseClickedProperty().set(event -> popUpPane.toBack());
       popUpConfirmationButton.onMouseClickedProperty().set(event ->  goToConfirmationStep());


       adressCircle.onMouseClickedProperty().set(event -> goToAdressStep());
       adressRectangle.onMouseClickedProperty().set(event -> goToAdressStep());
       deliveryCircle.onMouseClickedProperty().set(event -> goToDeliveryStep());
       deliveryRectangle.onMouseClickedProperty().set(event -> goToDeliveryStep());
       paymentCircle.onMouseClickedProperty().set(event -> goToPaymentStep());
       paymentRectangle.onMouseClickedProperty().set(event -> goToPaymentStep());
       lastCheckCircle.onMouseClickedProperty().set(event -> goToCheckStep());
       lastCheckRectangle.onMouseClickedProperty().set(event -> goToCheckStep());
       seeOrderImageView.onMouseClickedProperty().set(event -> goSeeOrder());

       controlChangeAdressText.onMouseClickedProperty().set(event -> goToAdressStep());
       controlChangeDeliveryText.onMouseClickedProperty().set(event -> goToDeliveryStep());
       controlChangePaymentText.onMouseClickedProperty().set(event -> goToPaymentStep());
       this.mainController = mainController;

       addressTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlAdressText, "Adress", newValue);});

       postcodeTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlPostcodeText, "Postkod", newValue);});

       cityTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlCityText, "Ort", newValue);});

       apartmentNumberTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlApartmentNumberText, "Lägenhetsnummer", newValue);});

      deliveryDateRadioButton1.onMouseClickedProperty().set(event -> setTextField(controlDeliveryDateText,
              "Leveransdag", String.valueOf(LocalDate.now().plusDays(3))));
       deliveryDateRadioButton2.onMouseClickedProperty().set(event -> setTextField(controlDeliveryDateText,
               "Leveransdag", String.valueOf(LocalDate.now().plusDays(4))));
       deliveryDateRadioButton3.onMouseClickedProperty().set(event -> setTextField(controlDeliveryDateText,
               "Leveransdag", String.valueOf(LocalDate.now().plusDays(5))));
       deliveryDateRadioButton4.onMouseClickedProperty().set(event -> setTextField(controlDeliveryDateText,
               "Leveransdag", String.valueOf(LocalDate.now().plusDays(6))));
       deliveryDateRadioButton5.onMouseClickedProperty().set(event -> setTextField(controlDeliveryDateText,
               "Leveransdag", String.valueOf(LocalDate.now().plusDays(7))));
       deliveryDateRadioButton6.onMouseClickedProperty().set(event -> setTextField(controlDeliveryDateText,
               "Leveransdag", String.valueOf(LocalDate.now().plusDays(8))));
       deliveryDateRadioButton7.onMouseClickedProperty().set(event -> setTextField(controlDeliveryDateText,
               "Leveransdag", String.valueOf(LocalDate.now().plusDays(9))));

       deliveryTimeRadioButton1.onMouseClickedProperty().set(event ->
               setTextField(controlDeliveryTimeText, "Tid", "10:00"));
       deliveryTimeRadioButton2.onMouseClickedProperty().set(event ->
               setTextField(controlDeliveryTimeText, "Tid", "11:00"));
       deliveryTimeRadioButton3.onMouseClickedProperty().set(event ->
               setTextField(controlDeliveryTimeText, "Tid", "12:00"));
       deliveryTimeRadioButton4.onMouseClickedProperty().set(event ->
               setTextField(controlDeliveryTimeText, "Tid", "14:00"));
       deliveryTimeRadioButton5.onMouseClickedProperty().set(event ->
               setTextField(controlDeliveryTimeText, "Tid", "15:00"));
       deliveryTimeRadioButton6.onMouseClickedProperty().set(event ->
               setTextField(controlDeliveryTimeText, "Tid", "16:00"));
       deliveryTimeRadioButton7.onMouseClickedProperty().set(event ->
               setTextField(controlDeliveryTimeText, "Tid", "17:00"));

       cardNumberTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlCardNumberText, "Kortnummer", newValue);});
       cardNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlCardName, "Namn på kort", newValue);});
       expirationDateTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlCardNumberText, "Giltigt till", newValue);});
       cvcTextField.textProperty().addListener((observable, oldValue, newValue) ->
       {setTextField(controlCvcCode, "cvc", newValue);});





   }

    private void setTextField(Text text, String string, String newValue) {
       text.setText(string + ": " + newValue);
    }

   public void openPopUp() {
        popUpPane.toFront();}

    public void goSeeOrder() {mainController.openOrderPage();}

    public void goToAdressStep() {adressStep.toFront();
        changeStepText("Adressinformation", 1);
        changeCircleAndRec(adressRectangle, adressCircle);}

    public void goToDeliveryStep() {deliveryStep.toFront();
        changeStepText("Leverans",2);
        changeCircleAndRec(deliveryRectangle, deliveryCircle);}

    public void goToPaymentStep() {paymentStep.toFront();
        changeStepText("Betalningsinformation", 3);
        changeCircleAndRec(paymentRectangle, paymentCircle);}

    public void goToCheckStep() {checkupStep.toFront();
        changeStepText("Sista Kontroll", 4);
        changeCircleAndRec(lastCheckRectangle, lastCheckCircle);}

    public void goToConfirmationStep() {
        changeStepText("Betalning genomförd", 5);
       popUpPane.toBack();
       confirmationStep.toFront();
       wrapper.getOrders().add(wrapper.placeOrder());
        changeCircleAndRec(affirmationRectangle, affirmationCircle);
        System.out.println();

   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup dateToggleGroup = new ToggleGroup();
        ToggleGroup timeToggleGroup = new ToggleGroup();
        ToggleGroup paymentMethodToggleGroup = new ToggleGroup();

        deliveryDateRadioButton1.setToggleGroup(dateToggleGroup);
        deliveryDateRadioButton2.setToggleGroup(dateToggleGroup);
        deliveryDateRadioButton3.setToggleGroup(dateToggleGroup);
        deliveryDateRadioButton4.setToggleGroup(dateToggleGroup);
        deliveryDateRadioButton5.setToggleGroup(dateToggleGroup);
        deliveryDateRadioButton6.setToggleGroup(dateToggleGroup);
        deliveryDateRadioButton7.setToggleGroup(dateToggleGroup);
        deliveryTimeRadioButton1.setToggleGroup(timeToggleGroup);
        deliveryTimeRadioButton2.setToggleGroup(timeToggleGroup);
        deliveryTimeRadioButton3.setToggleGroup(timeToggleGroup);
        deliveryTimeRadioButton4.setToggleGroup(timeToggleGroup);
        deliveryTimeRadioButton5.setToggleGroup(timeToggleGroup);
        deliveryTimeRadioButton6.setToggleGroup(timeToggleGroup);
        deliveryTimeRadioButton7.setToggleGroup(timeToggleGroup);
        paymentOptionCardRadioButton.setToggleGroup(paymentMethodToggleGroup);
        swishRadioButton.setToggleGroup(paymentMethodToggleGroup);
        eInvoiceRadioButton.setToggleGroup(paymentMethodToggleGroup);
        invoiceRadioButton.setToggleGroup(paymentMethodToggleGroup);
        setLastRectangleAndCircle(adressRectangle, adressCircle);






   }


}
