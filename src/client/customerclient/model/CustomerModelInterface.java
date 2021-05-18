package client.customerclient.model;

import shared.network.Subject;
import shared.wares.Basket;
import shared.wares.Product;

import java.util.ArrayList;

public interface CustomerModelInterface extends Subject
{

  void updateWares();
  void addToBasket(Product product, int amount);
  void removeFromBasket(Product product);
  void changeAmount(Product product, int newAmount);
  Basket getMyBasket();
  boolean sendOrder(Basket basket, double sum);
  ArrayList<Product> getAllWares();
  ArrayList<Product> getCategory(String category);
}
