package client.customerclient.views.customerbasket;


import client.core.ModelFactory;
import client.customerclient.model.CustomerModelInterface;
import client.customerclient.views.CustomerViewModel;
import javafx.util.Pair;
import shared.wares.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;

// Andreas Young, Andreas Østergaard

public class BasketViewModel implements CustomerViewModel {
	private CustomerModelInterface customerModel;

	public BasketViewModel() {
		customerModel = ModelFactory.getInstance().getCustomerModel();
	}

	public void removeFromBasket(List list) {
		for (Object o : list) {
			removeFromBasket(o);
		}
	}

	public void removeFromBasket(Object item) {
		if (item instanceof Product) item = new ProductAndInt(((Product) item).getWareName(),((Product) item).getWareNumber(),1);
		ProductAndInt selected = (ProductAndInt) item;
		customerModel.getMyBasket().removeProductWithSameWareNum(selected.getProductID());
	}

	public Pair<Boolean, ArrayList<Product>> sendOrder() {
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
