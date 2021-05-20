import client.grosserclient.model.GrosserModel;
import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
import client.network.GrosserClient;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import shared.wares.Alcohol;
import shared.wares.Product;

import java.time.LocalDate;

//Line Guld
class GrosserTestAddProduct
{
  private GrosserAddProductViewModel viewModel;
  private GrosserClient grosserClient;

  @BeforeEach public void setup()
  {
    GrosserModel model = new GrosserModel(grosserClient);
    viewModel = new GrosserAddProductViewModel();
  }

  //Test for om programmet opretter produktet når det får rigtigt input
  @Test void validAlcoholProductInput()
  {
    Alcohol validInputAlcohol = new Alcohol("Oxholm moussé", "kasser af 6", LocalDate
        .now(), 0, 30,350.00, 1, "Oxholm Gods", "Danmark", 12.50, "Rose");
    Integer amount = 500;
    Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol,amount);

    viewModel.sendOrder(validPair);


  }
}
