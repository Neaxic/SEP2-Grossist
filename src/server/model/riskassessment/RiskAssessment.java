package server.model.riskassessment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

//Line Guld og Frederik Bergmann

public class RiskAssessment implements RiskInterface
{
  private ArrayList<RiskReport> allReports, yellowReports, redReports;

  public ArrayList<RiskReport> massAssess(
      ArrayList<RiskContainer> itemList)
  {
    allReports = new ArrayList<>();
    yellowReports = new ArrayList<>();
    redReports = new ArrayList<>();

    for (RiskContainer item : itemList)
    {
      riskAssess(item);
    }

    return allReports;
  }

  private void riskAssess(RiskContainer item)
  {
    int soldDaily = item.getSoldDaily();
    int stock = item.getAmountInStock();
    int dDays = item.getDeliveryDays();
    LocalDate BB = item.getBestBefore();

    LocalDate today = LocalDate.now();
    long daysToBB = ChronoUnit.DAYS.between(today, BB);

    double daysOfSupply = (double) stock / soldDaily;

    if (daysOfSupply <= dDays + 3)
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

  public ArrayList<RiskReport> getAllReports()
  {
    return allReports;
  }

  public ArrayList<RiskReport> getYellowReports()
  {
    return yellowReports;
  }

  public ArrayList<RiskReport> getRedReports()
  {
    return redReports;
  }
}
