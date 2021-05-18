package client.grosserclient.model;

import client.network.Client;
import client.network.RMIClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GrosserModel implements GrosserModelInterface
{
  private PropertyChangeSupport Support;
  private final RMIClient client;

  public GrosserModel(Client client)
  {
    Support = new PropertyChangeSupport(this);
    this.client = (RMIClient) client;
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
}
