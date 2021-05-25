import client.core.factories.ClientFactory;
import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
import client.network.GrosserClient;

import static org.junit.jupiter.api.Assertions.*;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.Alcohol;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;

public class RiskAssessmentTest
{
  private static GrosserClient grosserClient;
  static GrosserModelInterface grosserModel;

  @BeforeAll //Hapset fra Young
  static void init()
  {
    try
    {
      RMIServerInterface server = new RMIServer();
      assertDoesNotThrow(server::startServer);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    ClientFactory.getInstance().getClient().start();
  }

  @Test void stockJustRight(){
    Alcohol justRightAlcohol = new Alcohol(
        "Dom Perignon 2010, 75cl",
        "kasser af 6",
        LocalDate.now().plusYears(3),
        -1,
        30,
        7170,
        "Moët & Chandon",
        "",
        "Frankrig",
        12.50,
        "Champagne");

    int soldDaily = 5;
    int stock = 150;
    Pair<Product, Integer> pair = new Pair<>(justRightAlcohol, stock);

    assertEquals("Green", riskAssess(pair, soldDaily));

  }

  @Test void oneTooLittleStock(){
    Alcohol notEnoughAlcohol = new Alcohol(
        "Dom Perignon 2010, 75cl",
        "kasser af 6",
        LocalDate.now().plusYears(3),
        -1,
        10,
        7170,
        "Moët & Chandon",
        "",
        "Frankrig",
        12.50,
        "Champagne");

    int soldDaily = 5;
    int stock = 49;
    Pair<Product, Integer> pair = new Pair<>(notEnoughAlcohol, stock);

    assertEquals("Yellow", riskAssess(pair, soldDaily));
  }

  @Test void wayTooLittleStock(){
    Alcohol notEnoughAlcohol = new Alcohol(
        "Dom Perignon 2010, 75cl",
        "kasser af 6",
        LocalDate.now().plusYears(3),
        -1,
        10,
        7170,
        "Moët & Chandon",
        "",
        "Frankrig",
        12.50,
        "Champagne");

    int soldDaily = 5;
    int stock = 5;
    Pair<Product, Integer> pair = new Pair<>(notEnoughAlcohol, stock);

    assertEquals("Yellow", riskAssess(pair, soldDaily));
  }

  @Test void tooMuchStock(){

  }

  @Test void  stockOneTooMuch(){

  }

  @Test void dateSlipping(){

  }
}
