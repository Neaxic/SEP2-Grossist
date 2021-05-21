//import client.core.factories.ClientFactory;
//import client.core.factories.ModelFactory;
//import client.grosserclient.model.GrosserModel;
//import client.grosserclient.model.GrosserModelInterface;
//import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
//import client.network.GrosserClient;
//import javafx.util.Pair;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import server.network.RMIServer;
//import shared.network.RMIServerInterface;
//import shared.wares.*;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
////Line Guld
//class GrosserTestAddProduct
//{
//  private static GrosserAddProductViewModel viewModel;
//  private static GrosserClient grosserClient;
//  static GrosserModelInterface grosserModel;
//  static Integer validAmount = 500;
//  static Integer invalidAmount = -1;
//  static Integer invalidAmountZero = 0;
//  static LocalDate validBestBefore = LocalDate.now().plusMonths(3);
//  static LocalDate invalidBestBeforeYesterday = LocalDate.now().minusDays(1);
//  static LocalDate invalidBestBeforeIsToday = LocalDate.now();
//  static Pair<Product, Integer> pair;
//
//  @BeforeAll //Hapset fra Young
//  static void init()
//  {
//    try
//    {
//      RMIServerInterface server = new RMIServer();
//      assertDoesNotThrow(server::startServer);
//    }
//    catch (SQLException throwable)
//    {
//      throwable.printStackTrace();
//    }
//    ClientFactory.getInstance().getClient().start();
//  }
//
//  @BeforeEach public void setup()
//  {
//    viewModel = new GrosserAddProductViewModel();
//    grosserModel = ModelFactory.getInstance().getGrosserModel();
//  }
//
//  static class AlcoholTest
//  {
//    @BeforeAll //Hapset fra Young
//    static void init()
//    {
//      try
//      {
//        RMIServerInterface server = new RMIServer();
//        assertDoesNotThrow(server::startServer);
//      }
//      catch (SQLException throwable)
//      {
//        throwable.printStackTrace();
//      }
//      ClientFactory.getInstance().getClient().start();
//    }
//
//    @BeforeEach public void setup()
//    {
//      viewModel = new GrosserAddProductViewModel();
//      grosserModel = ModelFactory.getInstance().getGrosserModel();
//    }
//
//  @Test void invalidAlcoholAmountInput()
//  {
//    Alcohol validInputAlcohol = new Alcohol(
//        "Dom Perignon 2010, 75cl",
//        "kasser af 6",
//        validBestBefore,
//        0,
//        30,
//        7170,
//        1,
//        "Moët & Chandon",
//        "Frankrig",
//        12.50,
//        "Champagne");
//
//    Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol,
//        invalidAmount);
//
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//  }
//
//    @Test void invalidAlcoholAmountZero()
//    {
//      Alcohol validInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol,
//          invalidAmountZero);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//  @Test void validAlcoholProductInput()
//  {
//    Alcohol validInputAlcohol = new Alcohol(
//        "Dom Perignon 2010, 75cl",
//        "kasser af 6",
//        validBestBefore,
//        0,
//        30,
//        7170,
//        1,
//        "Moët & Chandon",
//        "Frankrig",
//        12.50,
//        "Champagne");
//
//    Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol, validAmount);
//
//    assertDoesNotThrow(() -> viewModel.sendOrder(validPair));
//  }
//
//  @Test void invalidAlcoholProductInputName()
//  {
//    Alcohol invalidInputAlcohol = new Alcohol(
//        "",
//        "kasser af 6",
//        validBestBefore,
//        0,
//        30,
//        7170,
//        1,
//        "Moët & Chandon",
//        "Frankrig",
//        12.50,
//        "Champagne");
//
//    Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//  }
//
//    @Test void invalidAlcoholProductInputMeasurement()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "",
//          validBestBefore,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholProductBestBeforeYesterday()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          invalidBestBeforeYesterday,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholProductBestBeforeToday()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          invalidBestBeforeIsToday,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholDeliveryZero()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          0,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholDeliveryNegative()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          -1,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholPriceZero()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          0,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholPriceNegative()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          -1,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholInputProducedBy()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          7170,
//          1,
//          "",
//          "Frankrig",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholInputOrigin()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "",
//          12.50,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholInputPercentageZero()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          0,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidAlcoholInputPercentageNegative()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          -0.05,
//          "Champagne");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//    @Test void invalidAlcoholInputType()
//    {
//      Alcohol invalidInputAlcohol = new Alcohol(
//          "Dom Perignon 2010, 75cl",
//          "kasser af 6",
//          validBestBefore,
//          0,
//          30,
//          7170,
//          1,
//          "Moët & Chandon",
//          "Frankrig",
//          12.50,
//          "");
//
//      Pair<Product, Integer> validPair = new Pair<>(invalidInputAlcohol, validAmount);
//
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(validPair));
//    }
//
//}
//
//  static class DrinksTest
//  {
//    @BeforeAll //Hapset fra Young
//    static void init()
//    {
//      try
//      {
//        RMIServerInterface server = new RMIServer();
//        assertDoesNotThrow(server::startServer);
//      }
//      catch (SQLException throwable)
//      {
//        throwable.printStackTrace();
//      }
//      ClientFactory.getInstance().getClient().start();
//    }
//
//    @BeforeEach public void setup()
//    {
//      viewModel = new GrosserAddProductViewModel();
//      grosserModel = ModelFactory.getInstance().getGrosserModel();
//    }
//    // Test af at oprette ikke-alkoholiske drikkevarer
//    @Test void invalidDrinkAmountInput()
//    {
//      Drink validDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          360,
//          1,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(validDrink, invalidAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkAmountZero()
//    {
//      Drink validDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          360,
//          1,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(validDrink, invalidAmountZero);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void validDrinkProductInput()
//    {
//      Drink validDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          360,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> validPair = new Pair<>(validDrink, validAmount);
//      assertDoesNotThrow(() -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidDrinkProductInputName()
//    {
//      Drink invalidDrink = new Drink(
//          "",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          360,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductMeasurement()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "",
//          validBestBefore,
//          0,
//          7,
//          360,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductBestBeforeYesterday()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          invalidBestBeforeYesterday,
//          0,
//          7,
//          360,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductBestBeforeToday()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          invalidBestBeforeIsToday,
//          0,
//          7,
//          360,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductDeliveryZero()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          0,
//          360,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductDeliveryNegative()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          -1,
//          360,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductPriceZero()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          0,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductPriceNegative()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          -1,
//          10,
//          "Energidrik",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductType()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          360,
//          10,
//          "",
//          "Monster Beverage");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDrinkProductProducedBy()
//    {
//      Drink invalidDrink = new Drink(
//          "Monster pipeline punch",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          7,
//          360,
//          10,
//          "Energidrik",
//          "");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//  }
//
//  static class ColonialTest
//  {
//    @BeforeAll //Hapset fra Young
//    static void init()
//    {
//      try
//      {
//        RMIServerInterface server = new RMIServer();
//        assertDoesNotThrow(server::startServer);
//      }
//      catch (SQLException throwable)
//      {
//        throwable.printStackTrace();
//      }
//      ClientFactory.getInstance().getClient().start();
//    }
//
//    @BeforeEach public void setup()
//    {
//      viewModel = new GrosserAddProductViewModel();
//      grosserModel = ModelFactory.getInstance().getGrosserModel();
//    }
//    @Test void invalidColonialAmountInput()
//    {
//      Colonial validColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          1,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(validColonial, invalidAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialAmountZero()
//    {
//      Colonial validColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          1,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(validColonial, invalidAmountZero);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void validColonialInput()
//    {
//      Colonial validColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          150,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> validPair = new Pair<>(validColonial, validAmount);
//      assertDoesNotThrow(() -> viewModel.sendOrder(validPair));
//    }
//
//    @Test void invalidColonialInputName()
//    {
//      Colonial invalidColonial = new Colonial(
//          "",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          150,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputMeasurement()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "",
//          validBestBefore,
//          0,
//          1,
//          150,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputBestBeforeYesterday()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          invalidBestBeforeYesterday,
//          0,
//          1,
//          150,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputBestBeforeToday()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          invalidBestBeforeIsToday,
//          0,
//          1,
//          150,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputDeliveryDaysNegative()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          -1,
//          150,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputDeliveryDaysZero()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          invalidBestBeforeIsToday,
//          0,
//          0,
//          150,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputPriceNegative()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          -1,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputPriceZero()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          0,
//          10,
//          "Den gamle fabrik",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputProducedBy()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          50,
//          10,
//          "",
//          "Danmark");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidColonialInputOrigin()
//    {
//      Colonial invalidColonial = new Colonial(
//          "Jordbærsyltetøj",
//          "Ramme af 24",
//          validBestBefore,
//          0,
//          1,
//          60,
//          10,
//          "Den gamle fabrik",
//          "");
//
//      Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//  }
//  static class DairyTest{
//    @BeforeAll //Hapset fra Young
//    static void init()
//    {
//      try
//      {
//        RMIServerInterface server = new RMIServer();
//        assertDoesNotThrow(server::startServer);
//      }
//      catch (SQLException throwable)
//      {
//        throwable.printStackTrace();
//      }
//      ClientFactory.getInstance().getClient().start();
//    }
//
//    @BeforeEach public void setup()
//    {
//      viewModel = new GrosserAddProductViewModel();
//      grosserModel = ModelFactory.getInstance().getGrosserModel();
//    }
//  //Test mejeri og æg
//  @Test void invalidDairyAmountInput(){
//    CooledAndDairy validDairy = new CooledAndDairy(
//        "Arla sødmælk",
//        "Kasse af 15",
//        validBestBefore,
//        0,
//        1,
//        50.95,
//        20,
//        "Arla"
//    );
//
//    pair = new Pair<>(validDairy, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//    @Test void invalidDairyAmountInputZero(){
//      CooledAndDairy validDairy = new CooledAndDairy(
//          "Arla sødmælk",
//          "Kasse af 15",
//          validBestBefore,
//          0,
//          1,
//          50.95,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(validDairy, invalidAmountZero);
//      assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//    }
//
//  @Test void validDairyInput(){
//   CooledAndDairy validDairy = new CooledAndDairy(
//        "Arla sødmælk",
//        "Kasse af 15",
//        validBestBefore,
//        0,
//        1,
//        50.95,
//        20,
//        "Arla"
//    );
//     pair = new Pair<>(validDairy, validAmount);
//    assertDoesNotThrow(() -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidDairyInputName(){
//    CooledAndDairy invalidDairy = new CooledAndDairy(
//        "",
//        "Kasse af 15",
//        validBestBefore,
//        0,
//        1,
//        50.95,
//        20,
//        "Arla"
//    );
//
//    pair = new Pair<>(invalidDairy, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//    @Test void invalidDairyInputMeasurement(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "Sødmælk",
//          "",
//          validBestBefore,
//          0,
//          1,
//          50.95,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDairyInputBestBeforeYesterday(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "Sødmælk",
//          "Kasse af 15",
//          invalidBestBeforeYesterday,
//          0,
//          1,
//          50.95,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDairyInputBestBeforeToday(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "Sødmælk",
//          "Kasse af 15",
//          invalidBestBeforeIsToday,
//          0,
//          1,
//          50.95,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDairyInputDeliveryZero(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "Sødmælk",
//          "Kasse af 15",
//          validBestBefore,
//          0,
//          0,
//          50.95,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDairyDeliveryNegative(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "",
//          "Kasse af 15",
//          validBestBefore,
//          0,
//          -1,
//          50.95,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDairyInputPriceZero(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "Sødmælk",
//          "Kasse af 15",
//          validBestBefore,
//          0,
//          1,
//          0,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDairyInputPriceNegative(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "Sødmælk",
//          "Kasse af 15",
//          validBestBefore,
//          0,
//          1,
//          -1,
//          20,
//          "Arla"
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//
//    @Test void invalidDairyInputProducedBy(){
//      CooledAndDairy invalidDairy = new CooledAndDairy(
//          "Sødmælk",
//          "Kasse af 15",
//          validBestBefore,
//          0,
//          1,
//          50.14,
//          20,
//          ""
//      );
//
//      pair = new Pair<>(invalidDairy, validAmount);
//      assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//    }
//  }
//
//  static class GreensTest{
//    @BeforeAll //Hapset fra Young
//    static void init()
//    {
//      try
//      {
//        RMIServerInterface server = new RMIServer();
//        assertDoesNotThrow(server::startServer);
//      }
//      catch (SQLException throwable)
//      {
//        throwable.printStackTrace();
//      }
//      ClientFactory.getInstance().getClient().start();
//    }
//
//    @BeforeEach public void setup()
//    {
//      viewModel = new GrosserAddProductViewModel();
//      grosserModel = ModelFactory.getInstance().getGrosserModel();
//    }
//  // Test frugt og grønt
//  @Test void invalidGreensAmount(){
//    FruitsAndVegetable validFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        validBestBefore,
//        0,
//        7,
//        50.05,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(validFruit, invalidAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensAmountZero(){
//    FruitsAndVegetable validFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        validBestBefore,
//        0,
//        7,
//        50.05,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(validFruit, invalidAmountZero);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void validGreensInput(){
//    FruitsAndVegetable validFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        validBestBefore,
//        0,
//        7,
//        50.05,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(validFruit, validAmount);
//    assertDoesNotThrow(() -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensName(){
//  FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//      "",
//      "Kasse af 3 kg",
//      validBestBefore,
//      0,
//      7,
//      50.05,
//      20,
//      "Itagu",
//      "Costa Rica"
//  );
//
//  pair = new Pair<>(invalidFruit, validAmount);
//  assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//}
//
//@Test void invalidGreensMeasurement(){
//  FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//      "Bananer",
//      "",
//      validBestBefore,
//      0,
//      7,
//      50.05,
//      20,
//      "Itagu",
//      "Costa Rica"
//  );
//
//  pair = new Pair<>(invalidFruit, validAmount);
//  assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//}
//
//  @Test void invalidGreensBestByYesterday(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        invalidBestBeforeYesterday,
//        0,
//        7,
//        50.05,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensBestByToday(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        invalidBestBeforeIsToday,
//        0,
//        7,
//        50.05,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensDeliveryDaysNegative(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        invalidBestBeforeYesterday,
//        0,
//        -1,
//        50.05,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensDeliveryDaysZero(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        invalidBestBeforeYesterday,
//        0,
//        0,
//        50.05,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensPriceNegative(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        invalidBestBeforeYesterday,
//        0,
//        7,
//        -1,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensPriceZero(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        invalidBestBeforeYesterday,
//        0,
//        7,
//        0,
//        20,
//        "Itagu",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensProducedBy(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        validBestBefore,
//        0,
//        7,
//        50.05,
//        20,
//        "",
//        "Costa Rica"
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidGreensCountry(){
//    FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
//        "Bananer",
//        "Kasse af 3 kg",
//        validBestBefore,
//        0,
//        7,
//        50.05,
//        20,
//        "Itagu",
//        ""
//    );
//
//    pair = new Pair<>(invalidFruit, validAmount);
//    assertThrows(IllegalArgumentException.class, () -> viewModel.sendOrder(pair));
//  }}
//
//  static  class MeatAndFishTests{
//    @BeforeAll //Hapset fra Young
//    static void init()
//    {
//      try
//      {
//        RMIServerInterface server = new RMIServer();
//        assertDoesNotThrow(server::startServer);
//      }
//      catch (SQLException throwable)
//      {
//        throwable.printStackTrace();
//      }
//      ClientFactory.getInstance().getClient().start();
//    }
//
//    @BeforeEach public void setup()
//    {
//      viewModel = new GrosserAddProductViewModel();
//      grosserModel = ModelFactory.getInstance().getGrosserModel();
//    }
//
//// Test af Kød og Fisk
//  @Test void invalidMeatAmount(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatAmountZero(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmountZero);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void validMeatInput(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, validAmount);
//    assertDoesNotThrow(() -> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatName(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatMeasurement(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "",
//        validBestBefore,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatBestBeforeYesterday(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg", invalidBestBeforeYesterday,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatBestBeforeToday(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        invalidBestBeforeIsToday,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatDeliverytimeNegative(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        -1,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatDeliverytimeZero(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        0,
//        10000,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatPriceZero(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        0,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatPriceNegative(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        -1,
//        1,
//        "Hiroshima",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatProducedBy(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        10000,
//        1,
//        "",
//        "Japan"
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }
//
//  @Test void invalidMeatOrigin(){
//    MeatAndFish validMeat = new MeatAndFish(
//        "Wagyu Filet Mignon",
//        "Pakker af 50 kg",
//        validBestBefore,
//        0,
//        70,
//        10000,
//        1,
//        "Hiroshima",
//        ""
//    );
//
//    pair = new Pair<>(validMeat, invalidAmount);
//    assertThrows(IllegalArgumentException.class, ()-> viewModel.sendOrder(pair));
//  }}
//}
