package client.grosserclient.views.grosseraddproduct;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import client.grosserclient.views.grossermain.GrosserMainViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import shared.wares.*;

import java.io.IOException;

// Frederik Bergmann

public class GrosserAddProductViewController implements GrosserViewController
{
  private ViewHandler viewHandler;
  private GrosserAddProductViewModel viewModel;

  @FXML private TextField productName;
  @FXML private TextField productPrice;
  @FXML private TextField ProductMinAmount;
  @FXML private TextField productMeasurement;
  @FXML private DatePicker productBestBefore;
  @FXML private TextField productDeliveryDays;

  @FXML private TextField AlcoholType;
  @FXML private TextField AlcoholPercent;
  @FXML private TextField AlcoholCountry;

  @FXML private TextField DrikType;
  @FXML private TextField ColonialCountry;
  @FXML private TextField GreenCountry;
  @FXML private TextField meatCountry;

  @FXML private TabPane tabPane;

  @Override public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    viewModel = (GrosserAddProductViewModel) ViewModelFactory.getInstance().grosserAddProductViewModel();

  }

  @FXML private void createProduct(){
    String className = tabPane.getSelectionModel().getSelectedItem().getText();
    if(className.equals("Alkohol")){
      Alcohol newProduct = new Alcohol(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0,Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), AlcoholCountry.getText(), Double.parseDouble(AlcoholPercent.getText()), AlcoholType.getText());
      viewModel.sendOrder(newProduct);
    } else if(className.equals("Drikkevarer")) {
      Drink newProduct = new Drink(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0,Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), DrikType.getText());
      viewModel.sendOrder(newProduct);
    } else if(className.equals("Kolonial")) {
      Colonial newProduct = new Colonial(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0,Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), ColonialCountry.getText());
      viewModel.sendOrder(newProduct);
    } else if(className.equals("Mejeri og æg")) {
      CooledAndDairy newProduct = new CooledAndDairy(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0,Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()));
      viewModel.sendOrder(newProduct);
    } else if(className.equals("Frugt og grønt")) {
      FruitsAndVegetable newProduct = new FruitsAndVegetable(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0,Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), GreenCountry.getText());
      viewModel.sendOrder(newProduct);
    } else if(className.equals("Kød og fisk")) {
      MeatAndFish newProduct = new MeatAndFish(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0,Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), meatCountry.getText());
      viewModel.sendOrder(newProduct);
    }
  }

  @Override public void swapScene(String sceneName) throws IOException
  {
    viewHandler.openView(sceneName);
  }
}
