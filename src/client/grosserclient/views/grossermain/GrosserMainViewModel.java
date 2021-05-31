package client.grosserclient.views.grossermain;

import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.GrosserViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import shared.objects.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GrosserMainViewModel implements GrosserViewModel,
    PropertyChangeListener
{
  private final GrosserModelInterface grosserModel;
  private final SimpleListProperty<Order> orderList;

  public GrosserMainViewModel()
  {
    orderList= new SimpleListProperty<>();
    grosserModel = ModelFactory.getInstance().getGrosserModel();
    grosserModel.addListener(this);
  }

  private void updateOrderList(ArrayList<Order> orderArrayList)
  {
    orderList.set(FXCollections.observableList(orderArrayList));
  }

  public void getAllOrders()
  {
    grosserModel.getAllOrders();
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
