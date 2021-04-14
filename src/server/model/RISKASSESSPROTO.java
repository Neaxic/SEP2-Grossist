package server.model;

import shared.wares.Product;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RISKASSESSPROTO
{

  public static RiskReport riskAssess(Product item)
  {
    LocalDate today = LocalDate.now(ZoneId.systemDefault());
    long daysToBb = ChronoUnit.DAYS.between(today, item.getBb());
    double daysOfStock = (double) item.getStock() / item.getSoldDaily();

    if (item.getDeliveryDays() >= daysOfStock)
    {
      return new RiskReport("yellow", "Stock too low, order new produce.");
    }

    if (daysOfStock > daysToBb)
    {
      return new RiskReport("red", "Too much stock of produce.");
    }

    return new RiskReport("green", "Everything is in order.");
  }

  public static ArrayList<RiskReport> massAssess(ArrayList<Product> items)
  {
    ArrayList<RiskReport> reports = new ArrayList<>();
    LocalDate today = LocalDate.now(ZoneId.systemDefault());

    for (Product item : items)
    {
      long daysToBb = ChronoUnit.DAYS.between(today, item.getBb());
      double daysOfStock = (double) item.getStock() / item.getSoldDaily();

      if (item.getDeliveryDays() >= daysOfStock)
      {
        reports.add(new RiskReport("red", "Stock too low, order new produce."));
      }
      else if (daysOfStock > daysToBb)
      {
        reports.add(new RiskReport("yellow", "Too much stock of produce."));
      }
      else
      {
        reports.add(new RiskReport("green", "Everything is in order."));
      }
    }

    return reports;
  }
}
