package client.grosserclient.views.grosseraddcustomer;

import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.GrosserViewModel;
import shared.objects.CustomerContainer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GrosserAddCustomerViewModel implements GrosserViewModel,
    PropertyChangeListener
{
  private final GrosserModelInterface grosserModel;

  public GrosserAddCustomerViewModel()
  {
    grosserModel = ModelFactory.getInstance().getGrosserModel();
    grosserModel.addListener(this);
  }

  public boolean addCustomer(CustomerContainer customer)
  {
    return grosserModel.addCustomer(customer);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
