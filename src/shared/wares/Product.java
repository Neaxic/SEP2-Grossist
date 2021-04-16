package shared.wares;

import javafx.util.Pair;

import java.time.LocalDate;

public abstract class Product
{
  private Pair<Integer, String> stock;
  private Pair<Double, String> price;
  private int soldDaily, deliveryDays;
  private LocalDate bb;

  public Product(Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price,
      LocalDate bb)
  {
    this.stock = stock;
    this.soldDaily = soldDaily;
    this.deliveryDays = deliveryDays;
    this.price = price;
    this.bb = bb;
  }
}
