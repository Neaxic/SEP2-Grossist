package client.customerclient.views.customerbasket;


import client.core.ModelFactory;
import client.customerclient.model.Model;
import client.customerclient.views.CustomerViewModel;
import shared.wares.Product;

import java.util.HashMap;
import java.util.MissingResourceException;

// Andreas Young, Andreas Østergaard

public class BasketViewModel implements CustomerViewModel {
	private Model model;

	public BasketViewModel() {
		model = (Model) ModelFactory.getInstance().getCustomerModel();
	}

	public void removeFromBasket(Object item) {
		ProductAndInt selected = (ProductAndInt) item;
		model.getMyBasket().removeProductWithSameWareNum(selected.getProductID());
	}

	public boolean sendOrder() {
		double sum = 0;
		if (model.getMyBasket().getBasket().keySet().isEmpty()) {
			throw new MissingResourceException("No items in Basket to Order", "BasketViewModel", "BVM");
		}
		for (Product i : model.getMyBasket().getBasket().keySet()) {
			sum += i.getPrice() * model.getMyBasket().getBasket().get(i);
		}
		return model.sendOrder(model.getMyBasket(), sum);
	}

	public HashMap<Product, Integer> loadAllProducts() {
		return model.getMyBasket().getBasket();
	}

}
