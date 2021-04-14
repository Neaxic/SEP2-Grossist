package client.core;

import client.CustomerClient.Views.ViewModel;
import client.CustomerClient.Views.placeorder.PlaceOrderViewModel;

public class ViewModelFactory {
    private final ModelFactory modelFactory;

    private PlaceOrderViewModel placeOrderViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public ViewModel placeOrderViewModel(){
        if(placeOrderViewModel == null){
            placeOrderViewModel = new PlaceOrderViewModel();
        }
        return placeOrderViewModel;
    }
}
