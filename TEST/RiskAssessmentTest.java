import client.core.factories.ClientFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.network.GrosserClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.model.riskassessment.RiskAssessment;
import server.model.riskassessment.RiskContainer;
import server.model.riskassessment.RiskInterface;
import server.model.riskassessment.RiskReport;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.Alcohol;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Line Guld og Frederik Bergmann

public class RiskAssessmentTest
{
  private static GrosserClient grosserClient;
  static GrosserModelInterface grosserModel;
  static RiskInterface risk;

  //Lige et par produkter til at teste på
  Alcohol red1 = new Alcohol(
      "Dom Perignon 2010, 75cl",
      "kasser af 6",
      LocalDate.now().plusDays(5),
      -1,
      1,
      7170,
      "Moët & Chandon",
      "",
      "Frankrig",
      12.50,
      "Champagne");
  int soldDaily_red1 = 5;
  int stock_red1 = 26;

  Alcohol yellow1 = new Alcohol(
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
  int soldDaily_yellow1 = 5;
  int stock_yellow1 = 49;

  @BeforeAll
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
    risk = new RiskAssessment();
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

    assertEquals(0, risk.massAssess(riskList).size());
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

    assertEquals("Yellow", risk.massAssess(riskList).get(0).getType());
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

    assertEquals("Yellow", risk.massAssess(riskList).get(0).getType());
  }

  @Test void  stockOneTooMuch(){
    Alcohol plentyAlcohol = new Alcohol(
        "Dom Perignon 2010, 75cl",
        "kasser af 6",
        LocalDate.now().plusDays(5),
        -1,
        1,
        7170,
        "Moët & Chandon",
        "",
        "Frankrig",
        12.50,
        "Champagne");

    int soldDaily = 5;
    int stock = 26;

    RiskContainer container = new RiskContainer(
        plentyAlcohol.getWareNumber(),
        plentyAlcohol.getDeliveryDays(),
        stock,
        soldDaily,
        plentyAlcohol.getBestBefore());

    ArrayList<RiskContainer> riskList = new ArrayList<>();
    riskList.add(container);

    assertEquals("Red", risk.massAssess(riskList).get(0).getType());
  }

  @Test void wayTooMuchStock(){
    Alcohol plentyAlcohol = new Alcohol(
        "Dom Perignon 2010, 75cl",
        "kasser af 6",
        LocalDate.now().plusDays(5),
        -1,
        1,
        7170,
        "Moët & Chandon",
        "",
        "Frankrig",
        12.50,
        "Champagne");

    int soldDaily = 5;
    int stock = 50;

    RiskContainer container = new RiskContainer(
        plentyAlcohol.getWareNumber(),
        plentyAlcohol.getDeliveryDays(),
        stock,
        soldDaily,
        plentyAlcohol.getBestBefore());

    ArrayList<RiskContainer> riskList = new ArrayList<>();
    riskList.add(container);

    assertEquals("Red", risk.massAssess(riskList).get(0).getType());
  }

  @Test void getYellowReports(){
    //Setting up a red product item
    RiskContainer container_red1 = new RiskContainer(
        red1.getWareNumber(),
        red1.getDeliveryDays(),
        stock_red1,
        soldDaily_red1,
        red1.getBestBefore());

    // setting up 2 yellow items
    RiskContainer container_yellow1 = new RiskContainer(
        yellow1.getWareNumber(),
        yellow1.getDeliveryDays(),
        stock_yellow1,
        soldDaily_yellow1,
        yellow1.getBestBefore());

    RiskContainer container_yellow2 = new RiskContainer(
        yellow1.getWareNumber(),
        yellow1.getDeliveryDays(),
        stock_yellow1,
        soldDaily_yellow1,
        yellow1.getBestBefore());

    // MassAssess on the 3 products
    ArrayList<RiskContainer> itemlist = new ArrayList<>();
    itemlist.add(container_red1);
    itemlist.add(container_yellow1);
    itemlist.add(container_yellow2);

    ArrayList<RiskReport> report = risk.massAssess(itemlist);

    //Asserting!
    assertEquals(2, risk.getYellowReports().size());
  }

  @Test void getRedReports(){
    //Setting up a red product item
    RiskContainer container_red1 = new RiskContainer(
        red1.getWareNumber(),
        red1.getDeliveryDays(),
        stock_red1,
        soldDaily_red1,
        red1.getBestBefore());

    // setting up 2 yellow items
    RiskContainer container_yellow1 = new RiskContainer(
        yellow1.getWareNumber(),
        yellow1.getDeliveryDays(),
        stock_yellow1,
        soldDaily_yellow1,
        yellow1.getBestBefore());

    RiskContainer container_yellow2 = new RiskContainer(
        yellow1.getWareNumber(),
        yellow1.getDeliveryDays(),
        stock_yellow1,
        soldDaily_yellow1,
        yellow1.getBestBefore());

    // MassAssess on the 3 products
    ArrayList<RiskContainer> itemlist = new ArrayList<>();
    itemlist.add(container_red1);
    itemlist.add(container_yellow1);
    itemlist.add(container_yellow2);

    ArrayList<RiskReport> report = risk.massAssess(itemlist);

    //Asserting!
    assertEquals(1, risk.getRedReports().size());
  }

  @Test void getAllReports(){
    RiskContainer container_red1 = new RiskContainer(
        red1.getWareNumber(),
        red1.getDeliveryDays(),
        stock_red1,
        soldDaily_red1,
        red1.getBestBefore());

    // setting up 2 yellow items
    RiskContainer container_yellow1 = new RiskContainer(
        yellow1.getWareNumber(),
        yellow1.getDeliveryDays(),
        stock_yellow1,
        soldDaily_yellow1,
        yellow1.getBestBefore());

    RiskContainer container_yellow2 = new RiskContainer(
        yellow1.getWareNumber(),
        yellow1.getDeliveryDays(),
        stock_yellow1,
        soldDaily_yellow1,
        yellow1.getBestBefore());

    // MassAssess on the 3 products
    ArrayList<RiskContainer> itemlist = new ArrayList<>();
    itemlist.add(container_red1);
    itemlist.add(container_yellow1);
    itemlist.add(container_yellow2);

    ArrayList<RiskReport> report = risk.massAssess(itemlist);

    //Asserting!
    assertEquals(3, risk.getAllReports().size());
  }
}
