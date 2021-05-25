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
import server.model.RISK_ASSESSMENT.RiskContainer;
import server.model.RISK_ASSESSMENT.RiskInterface;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.Alcohol;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Line og Frederik pair-programming baby

public class RiskAssessmentTest
{
  private static GrosserClient grosserClient;
  static GrosserModelInterface grosserModel;
  static RiskInterface risk;

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

    RiskContainer container = new RiskContainer(
        justRightAlcohol.getWareNumber(),
        justRightAlcohol.getDeliveryDays(),
        stock,
        soldDaily,
        justRightAlcohol.getBestBefore());

    ArrayList<RiskContainer> riskList = new ArrayList<>();
    riskList.add(container);

    assertEquals("Green", risk.massAssess(riskList));

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

    RiskContainer container = new RiskContainer(
        notEnoughAlcohol.getWareNumber(),
        notEnoughAlcohol.getDeliveryDays(),
        stock,
        soldDaily,
        notEnoughAlcohol.getBestBefore());

    ArrayList<RiskContainer> riskList = new ArrayList<>();
    riskList.add(container);

    assertEquals("Yellow", risk.massAssess(riskList));
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

    RiskContainer container = new RiskContainer(
        notEnoughAlcohol.getWareNumber(),
        notEnoughAlcohol.getDeliveryDays(),
        stock,
        soldDaily,
        notEnoughAlcohol.getBestBefore());

    ArrayList<RiskContainer> riskList = new ArrayList<>();
    riskList.add(container);

    assertEquals("Yellow", risk.massAssess(riskList));
  }

  @Test void tooMuchStock(){

  }

  @Test void  stockOneTooMuch(){

  }

  @Test void dateSlipping(){

  }
}
