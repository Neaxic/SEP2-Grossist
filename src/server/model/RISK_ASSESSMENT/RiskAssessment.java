package server.model.RISK_ASSESSMENT;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RiskAssessment
{
  private static ArrayList<RiskReport> allReports, yellowReports, redReports;

  public static ArrayList<RiskReport> massAssess(
      ArrayList<RiskContainer> itemList)
  {
    allReports = new ArrayList<>();
    yellowReports = new ArrayList<>();
    redReports = new ArrayList<>();

    for (RiskContainer item : itemList)
    {
      riskAssess(item);
    }

    System.out.println(allReports.size() == itemList.size());

    return allReports;
  }

  private static void riskAssess(RiskContainer item)
  {
    int soldDaily = item.getSoldDaily();
    int stock = item.getAmountInStock();
    int dDays = item.getDeliveryDays();
    int id = item.getProductId();
    LocalDate BB = item.getBestBefore();

    LocalDate today = LocalDate.now();
    long daysToBB = ChronoUnit.DAYS.between(today, BB);

    double daysOfSupply = (double) stock / soldDaily;

    if (daysOfSupply < dDays)
    {
      RiskReport report = new RiskReport(item, 'y');
      allReports.add(report);
      yellowReports.add(report);
    }

    if (daysOfSupply > daysToBB)
    {
      RiskReport report = new RiskReport(item, 'r');
      allReports.add(report);
      redReports.add(report);
    }

  }

  public static ArrayList<RiskReport> getAllReports()
  {
    return allReports;
  }

  public static ArrayList<RiskReport> getYellowReports()
  {
    return yellowReports;
  }

  public static ArrayList<RiskReport> getRedReports()
  {
    return redReports;
  }
}
