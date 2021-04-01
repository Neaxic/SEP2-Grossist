package shared.wares;

import java.util.Calendar;

public class Produce
{
  private int stock, soldDaily, deliveryDays;
  private double kgPrice;
  private Calendar bb;

  public Produce(int stock, int soldDaily, int deliveryDays, double kgPrice,
      Calendar bb)
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

  public int getSoldDaily()
  {
    return soldDaily;
  }

  public double getKgPrice()
  {
    return kgPrice;
  }

  public Calendar getBb()
  {
    return bb;
  }
}
