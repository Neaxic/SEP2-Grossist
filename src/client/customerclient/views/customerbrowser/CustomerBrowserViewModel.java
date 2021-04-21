package client.customerclient.views.customerbrowser;

import client.core.ModelFactory;
import client.customerclient.model.Model;
import client.customerclient.views.CustomerViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
		}

		//FIXME HJÆÆÆLP, jeg kan ikke trække et helt product ud, men jeg kan få et
		// varenummer eller titel fra viewet
	}

	public void populate(String category) {
		// TODO: Send to model and have model handle the Searching for the specific ware group and then let it send it as a support.firePropertyChange()
	}

	public SimpleListProperty<Product> activeItemListProperty() {
		return activeItemList;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		activeItemList.set(FXCollections.observableList((List<Product>) evt.getNewValue()));
	}
}
