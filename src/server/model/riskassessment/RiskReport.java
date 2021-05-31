package server.model.riskassessment;

//Line Guld og Frederik Bergmann

import java.io.Serializable;
import java.time.LocalDate;

public class RiskReport implements Serializable
{
  private String type;
  private final int productId, deliveryDays, amountInStock, soldDaily;
  private final LocalDate bestBefore;

  public RiskReport(RiskContainer item, char type)
  {
    productId = item.getProductId();
    deliveryDays = item.getDeliveryDays();
    amountInStock = item.getAmountInStock();
    soldDaily = item.getSoldDaily();
    bestBefore = item.getBestBefore();

    if (type == 'y') this.type = "Lager";
    if (type == 'r') this.type = "Dato";
  }

  public String getRecommendation()
  {
    if (type.equals("Lager"))
    {
      double daysOfSupply = (double) amountInStock / soldDaily;
      boolean critical = daysOfSupply <= deliveryDays;

      if (critical)
      {
        int emptyStorage = (int) Math.ceil(deliveryDays - daysOfSupply);

        return "Kritisk lagerbeholdning! Bestil straks!\nNuværende beholdning: " + amountInStock +
            "\nGennemsnitlig dagligt salg: " + soldDaily + "\nLager tømt cirka " + emptyStorage +
            " dage før vare kan leveres.";
      }
      else
      {
        int buffer = (int) Math.ceil(daysOfSupply - deliveryDays);

        return "Lav lagerbeholdning.\nNuværende beholdning: " + amountInStock +
            "\nGennemsnitlig dagligt salg: " + soldDaily + "\nCirka " + buffer +
            " dages salg tilbage før kritisk niveau.";
      }
    }
    return "Not done yet!";
  }

  public String getType()
  {
    return type;
  }

  public int getProductId()
  {
    return productId;
  }

  public int getDeliveryDays()
  {
    return deliveryDays;
  }

  public int getAmountInStock()
  {
    return amountInStock;
  }

  public int getSoldDaily()
  {
    return soldDaily;
  }

  public LocalDate getBestBefore()
  {
    return bestBefore;
  }
}
