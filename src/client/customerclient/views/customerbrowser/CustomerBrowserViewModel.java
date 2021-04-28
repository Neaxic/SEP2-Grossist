package client.customerclient.views.customerbrowser;

import client.core.ModelFactory;
import client.customerclient.model.Model;
import client.customerclient.views.CustomerViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import shared.wares.OLD_Product;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerBrowserViewModel implements CustomerViewModel, PropertyChangeListener {
	private Model model;
	private SimpleListProperty<Product> activeItemList;


	public CustomerBrowserViewModel() {
		model = (Model) ModelFactory.getInstance().getCustomerModel();
		activeItemList = new SimpleListProperty<>();
		model.addListener(this);
	}

	public void loadAllProducts() {
		// Load products from Database
		model.getAllWares();
	}

	public void addToBasket(String item, int amount) {
		if (item == null || item.equals("")) {
			System.out.println("Item error: " + item);
		} else if (amount <= 0) {
			System.out.println("Invalid amount");
		} else {
			System.out.println("added " + amount + " " + item + " to cart");

			// TODO: Tjek om søgning virker
			for (Product product : activeItemList) {
				if (product.getWareName().equals(item)) {
					model.addToBasket(null, amount); // Product typer er forskellige
//					model.addToBasket(product, amount);
				}
			}
		}
	}

	public void populate(String category) {
		// TODO: Send to model and have model handle the Searching for the specific ware group and then let it send it as a support.firePropertyChange()
	}

	public SimpleListProperty<Product> activeItemListProperty() {
		return activeItemList;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		HashMap<Product, Integer> waresFromServer = (HashMap<Product, Integer>) evt.getNewValue();
		List<Product> unitWares = new ArrayList<>(waresFromServer.keySet());
		activeItemList.set(FXCollections.observableList(unitWares));
//		activeItemList.set(FXCollections.observableList((List<Product>) evt.getNewValue()));
	}
}
