package client.grosserclient.model;

import javafx.util.Pair;
import shared.network.Subject;
import shared.wares.Product;

public interface GrosserModelInterface extends Subject
{
    public void createNewProduct(Pair<Product, Integer> newProduct);
  void getAllOrders();
}
