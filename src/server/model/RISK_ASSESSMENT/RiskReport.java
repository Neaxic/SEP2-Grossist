package server.model.RISK_ASSESSMENT;

//Line og Frederik

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

    if (type == 'g') this.type = "Green";
    if (type == 'y') this.type = "Yellow";
    if (type == 'r') this.type = "Red";
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
