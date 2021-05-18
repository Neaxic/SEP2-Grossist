package client.customerclient.views.customerbasket;


import client.core.ModelFactory;
import client.customerclient.model.CustomerModel;
import client.customerclient.model.CustomerModelInterface;
import client.customerclient.views.CustomerViewModel;
import shared.wares.Product;

import java.util.HashMap;
import java.util.MissingResourceException;

// Andreas Young, Andreas Østergaard

public class BasketViewModel implements CustomerViewModel {
	private CustomerModelInterface customerModel;

	public BasketViewModel() {
		customerModel = ModelFactory.getInstance().getCustomerModel();
	}

	public void removeFromBasket(Object item) {
		ProductAndInt selected = (ProductAndInt) item;
		customerModel.getMyBasket().removeProductWithSameWareNum(selected.getProductID());
	}

	public boolean sendOrder() {
		double sum = 0;
		if (customerModel.getMyBasket().getBasket().keySet().isEmpty()) {
			throw new MissingResourceException("No items in Basket to Order", "BasketViewModel", "BVM");
		}
		for (Product i : customerModel.getMyBasket().getBasket().keySet()) {
			sum += i.getPrice() * customerModel.getMyBasket().getBasket().get(i);
		}
		return customerModel.sendOrder(customerModel.getMyBasket(), sum);
	}

	public HashMap<Product, Integer> loadAllProducts() {
		return customerModel.getMyBasket().getBasket();
	}

}
