package client.grosserclient.model;

import shared.network.Subject;
import shared.wares.Product;

public interface GrosserModelInterface extends Subject
{
    public void createNewProduct(Product newProduct);
  void getAllOrders();
}
