package client.grosserclient.model;

import client.network.Client;
import client.network.RMIClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GrosserModel implements GrosserModelInterface
{
  private final RMIClient client;

  public GrosserModel(Client client)
  {
    this.client = (RMIClient) client;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
