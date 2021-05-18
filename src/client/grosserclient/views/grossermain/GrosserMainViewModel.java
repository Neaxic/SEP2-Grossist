package client.grosserclient.views.grossermain;

import client.grosserclient.views.GrosserViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import shared.wares.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GrosserMainViewModel implements GrosserViewModel,
    PropertyChangeListener
{

  private SimpleListProperty<Order> orderList;

  public GrosserMainViewModel()
  {
    orderList= new SimpleListProperty<>();
  }

  private void updateOrderList(ArrayList<Order> orderArrayList)
  {
    orderList.set(FXCollections.observableList(orderArrayList));
  }

  public SimpleListProperty<Order> getOrderListProperty()
  {
    return orderList;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("orderList"))
    {

    }
  }
}
