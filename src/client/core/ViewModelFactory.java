package client.core;

import client.customerclient.views.customerbrowser.CustomerBrowserViewModel;
import client.customerclient.views.CustomerViewModel;
import client.customerclient.views.customerbasket.BasketViewModel;
import client.customerclient.views.placeorder.PlaceOrderViewModel;

public class ViewModelFactory {
    private final ModelFactory modelFactory;

    private PlaceOrderViewModel placeOrderViewModel;
    private CustomerBrowserViewModel customerBrowseViewModel;
    private BasketViewModel basketViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public CustomerViewModel placeOrderViewModel(){
        if(placeOrderViewModel == null){
            placeOrderViewModel = new PlaceOrderViewModel();
        }
        return placeOrderViewModel;
    }

    public CustomerViewModel customerBrowseViewModel(){
        if(customerBrowseViewModel == null){
            customerBrowseViewModel = new CustomerBrowserViewModel();
        }
        return customerBrowseViewModel;
    }

    public CustomerViewModel basketViewModel(){
        if(basketViewModel == null){
            basketViewModel = new BasketViewModel();
        }
        return basketViewModel;
    }



}
