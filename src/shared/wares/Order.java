package shared.wares;

import java.time.LocalDate;

public class Order
{
  private final int CVR, orderNo;
  private final LocalDate orderDate;
  private final Double sum;

  private Basket basket;

  public Order(int CVR, int orderNo, LocalDate orderDate, Double sum,
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

  public Double getSum()
  {
    return sum;
  }

  public Basket getBasket()
  {
    return basket;
  }

}
