package client.customerclient.views.customerbasket;


import client.core.ModelFactory;
import client.customerclient.model.Model;
import client.customerclient.views.CustomerViewModel;
import javafx.collections.ObservableList;
import shared.wares.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketViewModel implements CustomerViewModel
{
	private Model model;

	public BasketViewModel(){
		model = (Model) ModelFactory.getInstance().getCustomerModel();
	}

	public void removeFromBasket(ObservableList<Object> list) {
	}

	public HashMap<Product, Integer> loadAllProducts(){
		return model.getMyBasket().getBasket();
	}
}
