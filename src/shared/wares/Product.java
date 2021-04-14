package shared.wares;

import java.time.LocalDate;

public class Product
{
  private int stock, soldDaily, deliveryDays;
  private double kgPrice;
  private LocalDate bb;

  public Product(int stock, int soldDaily, int deliveryDays, double kgPrice,
      LocalDate bb)
  {
    this.stock = stock;
    this.soldDaily = soldDaily;
    this.deliveryDays = deliveryDays;
    this.kgPrice = kgPrice;
    this.bb = bb;
  }

  public int getStock()
  {
    return stock;
  }

  public int getDeliveryDays()
  {
    return deliveryDays;
  }

  public int getSoldDaily()
  {
    return soldDaily;
  }

  public double getKgPrice()
  {
    return kgPrice;
  }

  public LocalDate getBb()
  {
    return bb;
  }
}
