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
import shared.wares.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Line Guld
class GrosserTestAddProduct
{
  private GrosserAddProductViewModel viewModel;
  private GrosserClient grosserClient;
  Integer validAmount = 500;
  Integer invalidAmount = -1; //ILLEGAL ARGUMENT HERE
LocalDate validBestBefore = LocalDate.now().plusMonths(3);
  Pair<Product, Integer> pair;

  @BeforeEach public void setup()
  {
    GrosserModel model = new GrosserModel(grosserClient);
    viewModel = new GrosserAddProductViewModel();
  }

  //Test af at oprette alkoholiske produkter
  @Test void validAlcoholProductInput()
  {
    Alcohol validInputAlcohol = new Alcohol(
        "Oxholm moussé",
        "kasser af 6",
        LocalDate.now().plusMonths(3),
        0,
        30,
        350.00,
        1,
        "Oxholm Gods",
        "Danmark",
        12.50,
        "Rose");

    Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol,validAmount);

    assertDoesNotThrow(() -> viewModel.sendOrder(validPair));
  }

  @Test void invalidAlcoholProductInput()
  {
    Alcohol validInputAlcohol = new Alcohol(
        "Oxholm moussé",
        "kasser af 6",
        LocalDate.now().plusMonths(3),
        0,
        30,
        0, //ILLEGAL ARGUMENT HERE
        1,
        "Oxholm Gods",
        "Danmark",
        12.50,
        "Rose");

    Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol,validAmount);

    assertThrows(IllegalArgumentException.class,  () -> viewModel.sendOrder(validPair));
  }

  @Test void invalidAlcoholAmountInput()
  {
    Alcohol validInputAlcohol = new Alcohol(
        "Oxholm moussé",
        "kasser af 6",
        LocalDate.now().plusMonths(3),
        0,
        30,
        200,
        1,
        "Oxholm Gods",
        "Danmark",
        12.50,
        "Rose");

    Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol,invalidAmount);

    assertThrows(IllegalArgumentException.class,  () -> viewModel.sendOrder(validPair));
  }

  // Test af at oprette ikke-alkoholiske drikkevarer
  @Test void validDrinkProductInput(){
    Drink validDrink = new Drink(
        "Monster pipeline punch",
        "Ramme af 24",
        LocalDate.now().plusMonths(3),
        0,
        7,
        360,
        10,
        "Energidrik",
        "Monster Beverage"
        );

    Pair<Product, Integer> validPair = new Pair<>(validDrink,validAmount);
    assertDoesNotThrow(() -> viewModel.sendOrder(validPair));
  }

  @Test void invalidDrinkProductInput(){
    Drink invalidDrink = new Drink(
        "Monster pipeline punch",
        "Ramme af 24",
        LocalDate.now().plusMonths(3),
        0,
        7,
        360,
        -1,
        "Energidrik",
        "Monster Beverage"
    );

    Pair<Product, Integer> pair = new Pair<>(invalidDrink,validAmount);
    assertThrows(IllegalArgumentException.class,  () -> viewModel.sendOrder(pair));
  }

  @Test void invalidDrinkAmountInput(){
    Drink validDrink = new Drink(
        "Monster pipeline punch",
        "Ramme af 24",
        LocalDate.now().plusMonths(3),
        0,
        7,
        360,
        1,
        "Energidrik",
        "Monster Beverage"
    );

    Pair<Product, Integer> pair = new Pair<>(validDrink,invalidAmount);
    assertThrows(IllegalArgumentException.class,  () -> viewModel.sendOrder(pair));
  }

  // WHip whoop Kolonial test
  @Test void validColonialInput(){
    Colonial validColonial = new Colonial(
        "Jordbærsyltetøj",
        "Ramme af 24",
        LocalDate.now().plusMonths(3),
        0,
        1,
        150,
        10,
        "Den gamle fabrik",
        "Danmark"
    );

    Pair<Product, Integer> validPair = new Pair<>(validColonial,validAmount);
    assertDoesNotThrow(() -> viewModel.sendOrder(validPair));
  }

  @Test void invalidColonialInput(){
    Colonial invalidColonial = new Colonial(
        "Jordbærsyltetøj",
        "Ramme af 24",
        LocalDate.now().plusMonths(3),
        0,
        1,
        -1,
        10,
        "Den gamle fabrik",
        "Danmark"
    );

    Pair<Product, Integer> pair = new Pair<>(invalidColonial,validAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidColonialAmountInput(){
    Colonial validColonial = new Colonial(
        "Jordbærsyltetøj",
        "Ramme af 24",
        LocalDate.now().plusMonths(3),
        0,
        1,
        1,
        10,
        "Den gamle fabrik",
        "Danmark"
    );

    Pair<Product, Integer> pair = new Pair<>(validColonial,invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  //Test mejeri og æg
  public CooledAndDairy validDairy = new CooledAndDairy(
      "Arla sødmælk",
      "Kasse af 15",
     validBestBefore,
      0,
      1,
      50.95,
      20,
      "Arla"
  );

  @Test void validDairyInput(){
     pair = new Pair<>(validDairy, validAmount);
    assertDoesNotThrow(() -> viewModel.sendOrder(pair));
  }

  @Test void invalidDairyInput(){
    CooledAndDairy invalidDairy = new CooledAndDairy(
        "Arla sødmælk",
        "Kasse af 15",
        validBestBefore,
        0,
        1,
        -1,
        20,
        "Arla"
    );

    pair = new Pair<>(invalidDairy, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidDairyAmountInput(){
    pair = new Pair<>(validDairy, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  // Test frugt og grønt
  public FruitsAndVegetable validFruit = new FruitsAndVegetable(
      "Bananer",
      "Kasse af 3 kg",
      validBestBefore,
      0,
      7,
      50.05,
      20,
      "Itagu",
      "Costa Rica"
  );

  @Test void validGreensInput(){
    pair = new Pair<>(validFruit, validAmount);
    assertDoesNotThrow(() -> viewModel.sendOrder(pair));
  }


}
