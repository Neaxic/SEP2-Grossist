package server.model.RISK_ASSESSMENT;

public class RiskContainer
{
  private final int productId, deliveryDays, amountInStock, soldDaily;

  public RiskContainer(int productId, int deliveryDays, int amountInStock,
      int soldDaily)
  {
    this.productId = productId;
    this.deliveryDays = deliveryDays;
    this.amountInStock = amountInStock;
    this.soldDaily = soldDaily;
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
}
