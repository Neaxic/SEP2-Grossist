package shared.wares;

import javafx.util.Pair;

import java.util.ArrayList;

public class Basket
{
  private final ArrayList<Pair<Product, Integer>> basket;

  public Basket()
  {
    basket = new ArrayList<>();
  }

  public void addProduct(Product product, int amount)
  {
    Pair<Product, Integer> ware = new Pair<>(product, amount);
    basket.add(ware);
  }

  public void removeProduct(Product product)
  {
    basket.removeIf(productIntegerPair -> productIntegerPair.getKey().equals(product));
  }

  public void changeAmount(Product product, int amount)
  {
    for (Pair<Product, Integer> productIntegerPair : basket)
    {
      if (productIntegerPair.getKey().equals(product))
      {
        basket.remove(productIntegerPair);
        Pair<Product, Integer> ware = new Pair<>(product, amount);
        basket.add(ware);
      }
    }
  }
}
