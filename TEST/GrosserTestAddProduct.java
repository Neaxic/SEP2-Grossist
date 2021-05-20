import client.core.factories.ClientFactory;
import client.grosserclient.model.GrosserModel;
import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
import client.network.GrosserClient;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.*;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Line Guld
class GrosserTestAddProduct
{
  private GrosserAddProductViewModel viewModel;
  private GrosserClient grosserClient;
  Integer validAmount = 500;
  Integer invalidAmount = -1;
  Integer invalidAmountZero = 0;
  LocalDate validBestBefore = LocalDate.now().plusMonths(3);
  LocalDate invalidBestBeforeYesterday = LocalDate.now().minusDays(1);
  LocalDate invalidBestBeforeIsToday = LocalDate.now();
  Pair<Product, Integer> pair;

  @BeforeAll //Hapset fra Young
  static void init() {
    try {
      RMIServerInterface server = new RMIServer();
      assertDoesNotThrow(server::startServer);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    ClientFactory.getInstance().getClient().start();
  }

  @BeforeEach public void setup()
  {
    GrosserModel model = new GrosserModel(grosserClient);
    viewModel = new GrosserAddProductViewModel();
  }

  //Test af at oprette alkoholiske produkter
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

  // Test af at oprette ikke-alkoholiske drikkevarer
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

  // WHip whoop Kolonial test
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

  //Test mejeri og æg
  @Test void invalidDairyAmountInput(){
    CooledAndDairy validDairy = new CooledAndDairy(
        "Arla sødmælk",
        "Kasse af 15",
        validBestBefore,
        0,
        1,
        50.95,
        20,
        "Arla"
    );

    pair = new Pair<>(validDairy, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void validDairyInput(){
   CooledAndDairy validDairy = new CooledAndDairy(
        "Arla sødmælk",
        "Kasse af 15",
        validBestBefore,
        0,
        1,
        50.95,
        20,
        "Arla"
    );
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

  // Test frugt og grønt

  @Test void invalidGreensAmount(){
    FruitsAndVegetable validFruit = new FruitsAndVegetable(
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

    pair = new Pair<>(validFruit, invalidAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensAmountZero(){
    FruitsAndVegetable validFruit = new FruitsAndVegetable(
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

    pair = new Pair<>(validFruit, invalidAmountZero);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void validGreensInput(){
    FruitsAndVegetable validFruit = new FruitsAndVegetable(
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

    pair = new Pair<>(validFruit, validAmount);
    assertDoesNotThrow(() -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensName(){
  FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
      "",
      "Kasse af 3 kg",
      validBestBefore,
      0,
      7,
      50.05,
      20,
      "Itagu",
      "Costa Rica"
  );

  pair = new Pair<>(invalidFruit, validAmount);
  assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
}

@Test void invalidGreensMeasurement(){
  FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
      "Bananer",
      "",
      validBestBefore,
      0,
      7,
      50.05,
      20,
      "Itagu",
      "Costa Rica"
  );

  pair = new Pair<>(invalidFruit, validAmount);
  assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
}

  @Test void invalidGreensBestByYesterday(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        invalidBestBeforeYesterday,
        0,
        7,
        50.05,
        20,
        "Itagu",
        "Costa Rica"
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensBestByToday(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        invalidBestBeforeIsToday,
        0,
        7,
        50.05,
        20,
        "Itagu",
        "Costa Rica"
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensDeliverydaysNegative(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        invalidBestBeforeYesterday,
        0,
        -1,
        50.05,
        20,
        "Itagu",
        "Costa Rica"
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensDeliverydaysZero(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        invalidBestBeforeYesterday,
        0,
        0,
        50.05,
        20,
        "Itagu",
        "Costa Rica"
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensPriceNegative(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        invalidBestBeforeYesterday,
        0,
        7,
        -1,
        20,
        "Itagu",
        "Costa Rica"
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensPriceZero(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        invalidBestBeforeYesterday,
        0,
        7,
        0,
        20,
        "Itagu",
        "Costa Rica"
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensProducedBy(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        validBestBefore,
        0,
        7,
        50.05,
        20,
        "",
        "Costa Rica"
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

  @Test void invalidGreensCountry(){
    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
        "Bananer",
        "Kasse af 3 kg",
        validBestBefore,
        0,
        7,
        50.05,
        20,
        "Itagu",
        ""
    );

    pair = new Pair<>(invalidFruit, validAmount);
    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
  }

// Test af Kød og Fisk
  @Test void invalidMeatAmount(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatAmountZero(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmountZero);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void validMeatInput(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, validAmount);
    assertDoesNotThrow(() -> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatName(){
    MeatAndFish validMeat = new MeatAndFish(
        "",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatMeasurement(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "",
        validBestBefore,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatBestBeforeYesterday(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg", invalidBestBeforeYesterday,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatBestBeforeToday(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        invalidBestBeforeIsToday,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatDeliverytimeNegative(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        -1,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatDeliverytimeZero(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        0,
        10000,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatPriceZero(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        0,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatPriceNegative(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        -1,
        1,
        "Hiroshima",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatProducedBy(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        10000,
        1,
        "",
        "Japan"
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }

  @Test void invalidMeatOrigin(){
    MeatAndFish validMeat = new MeatAndFish(
        "Wagyu Filet Mignon",
        "Pakker af 50 kg",
        validBestBefore,
        0,
        70,
        10000,
        1,
        "Hiroshima",
        ""
    );

    pair = new Pair<>(validMeat, invalidAmount);
    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
  }
}
