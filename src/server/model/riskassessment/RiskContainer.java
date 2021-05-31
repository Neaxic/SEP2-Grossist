package server.model.riskassessment;

import java.time.LocalDate;

//Line Guld og Frederik Bergmann

public class RiskContainer
{
  private final int productId, deliveryDays, amountInStock, soldDaily;
  private final LocalDate bestBefore;

  public RiskContainer(int productId, int deliveryDays, int amountInStock,
      int soldDaily, LocalDate bestBefore)
  {
    this.productId = productId;
    this.deliveryDays = deliveryDays;
    this.amountInStock = amountInStock;
    this.soldDaily = soldDaily;
    this.bestBefore = bestBefore;
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
