package shared.wares;

import java.util.Calendar;

public class Produce
{
  private int stock;
  private double kgPrice;
  private Calendar bb;

  public Produce(int stock, double kgPrice, Calendar bb)
  {
    this.stock = stock;
    this.kgPrice = kgPrice;
    this.bb = bb;
  }
}
