package client.core;

import client.customerclient.views.customerbrowser.CustomerBrowserViewModel;
import client.customerclient.views.CustomerViewModel;
import client.customerclient.views.customerbasket.BasketViewModel;
import client.customerclient.views.customerbrowser.CustomerBrowserViewModel;
import client.customerclient.views.placeorderEXCLUDED.PlaceOrderViewModel;
import client.grosserclient.views.GrosserViewModel;
import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
import client.grosserclient.views.grossermain.GrosserMainViewModel;

// Andreas Østergaard, Frederik Bergmann, Andreas Young

public class ViewModelFactory
{
  private static ViewModelFactory instance;
  private final ModelFactory modelFactory;

  private PlaceOrderViewModel placeOrderViewModel;
  private CustomerBrowserViewModel customerBrowseViewModel;
  private BasketViewModel basketViewModel;

  private GrosserMainViewModel grosserMainViewModel;
  private GrosserAddProductViewModel grosserAddProductViewModel;

	private ViewModelFactory() {
		this.modelFactory = ModelFactory.getInstance();
	}

  public static ViewModelFactory getInstance()
  {
    if (instance == null)
    {
      instance = new ViewModelFactory();
    }
    return instance;
  }

  public CustomerViewModel placeOrderViewModel()
  {
    if (placeOrderViewModel == null)
    {
      placeOrderViewModel = new PlaceOrderViewModel();
    }
    return placeOrderViewModel;
  }

  public CustomerViewModel customerBrowseViewModel()
  {
    if (customerBrowseViewModel == null)
    {
      customerBrowseViewModel = new CustomerBrowserViewModel();
    }
    return customerBrowseViewModel;
  }

  public CustomerViewModel basketViewModel()
  {
    if (basketViewModel == null)
    {
      basketViewModel = new BasketViewModel();
    }
    return basketViewModel;
  }

  public GrosserViewModel grosserMainViewModel()
  {
    if (grosserMainViewModel == null)
    {
      grosserMainViewModel = new GrosserMainViewModel();
    }
    return grosserMainViewModel;
  }

  public GrosserAddProductViewModel grosserAddProductViewModel()
  {
    if (grosserAddProductViewModel == null)
    {
      grosserAddProductViewModel = new GrosserAddProductViewModel();
    }
    return grosserAddProductViewModel;
  }
}