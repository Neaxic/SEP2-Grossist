package client.grosserclient.model;

import client.network.Client;
import client.network.GrosserClient;
import client.network.RMIClient;
import javafx.util.Pair;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GrosserModel implements GrosserModelInterface
{
  private PropertyChangeSupport Support;
  private final GrosserClient client;

  public GrosserModel(GrosserClient client)
  {
    Support = new PropertyChangeSupport(this);
    this.client = client;
    this.client.addListener(this);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    Support.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    Support.removePropertyChangeListener(listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("orderList"))
    {
      Support.firePropertyChange("orderList", null, evt.getNewValue());
    }
  }

  @Override public void getAllOrders()
  {
    client.getAllOrders();
  }

  @Override
  public void createNewProduct(Pair<Product, Integer> newProduct) {
    client.createProduct(newProduct);
  }
}
