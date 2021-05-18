package client.grosserclient.views.grossermain;

import client.core.ModelFactory;
import client.grosserclient.model.GrosserModel;
import client.grosserclient.model.GrosserModelInterface;
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
  private final GrosserModelInterface GrosserModel;
  private SimpleListProperty<Order> orderList;

  public GrosserMainViewModel()
  {
    orderList= new SimpleListProperty<>();
    GrosserModel = ModelFactory.getInstance().getGrosserModel();
    GrosserModel.addListener(this);
  }

  private void updateOrderList(ArrayList<Order> orderArrayList)
  {
    orderList.set(FXCollections.observableList(orderArrayList));
  }

  public void getAllOrders()
  {
    GrosserModel.getAllOrders();
  }

  public SimpleListProperty<Order> getOrderListProperty()
  {
    return orderList;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("orderList"))
    {
      updateOrderList((ArrayList<Order>) evt.getNewValue());
    }
  }
}
