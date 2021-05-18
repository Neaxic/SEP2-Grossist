package shared.wares;

import java.io.Serializable;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class Order implements Serializable
{
  private final int CVR, orderNo;
  private final LocalDate orderDate;
  private final double sum;

  private final Basket basket;

  public Order(int CVR, int orderNo, LocalDate orderDate, double sum,
      Basket basket)
  {
    this.CVR = CVR;
    this.orderNo = orderNo;
    this.orderDate = orderDate;
    this.sum = sum;
    this.basket = basket;
  }

  public int getCVR()
  {
    return CVR;
  }

  public int getOrderNo()
  {
    return orderNo;
  }

  public LocalDate getOrderDate()
  {
    return orderDate;
  }

  public double getSum()
  {
    return sum;
  }

  public Basket getBasket()
  {
    return basket;
  }

}
