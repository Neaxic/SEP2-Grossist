package server.model;

import shared.wares.Produce;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class RISKASSESSPROTO
{

  public static RiskReport riskAssess(Produce item)
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
}
