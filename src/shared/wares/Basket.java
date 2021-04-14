package shared.wares;

import java.util.ArrayList;

public class Basket
{
  private ArrayList<Products> shoppingList;
  private double total;

  public Basket(ArrayList<Products> shoppingList, double total)
  {
    this.shoppingList = shoppingList;
    this.total = total;
  }

  public void placeOrder(Basket basket){ }

 public void addProduct(Products product)
 {
   shoppingList.add(product);
 }

 public void removeProduct(Products product)
 {
   shoppingList.remove(product);
 }

  public ArrayList<Products> getShoppingList()
  {
    return shoppingList;
  }

  public double getTotal()
  {
    return total;
  }
}
