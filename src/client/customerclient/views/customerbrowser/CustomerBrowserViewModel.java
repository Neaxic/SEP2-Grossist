package client.customerclient.views.customerbrowser;

import client.customerclient.model.CustomerModelInterface;
import client.customerclient.views.CustomerViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Andreas Young, Line Guld

public class CustomerBrowserViewModel implements CustomerViewModel, PropertyChangeListener {
	private final CustomerModelInterface customerModelInterface;
	//gennem interface!
	private SimpleListProperty<Product> activeItemList;

	public CustomerBrowserViewModel(CustomerModelInterface customerModelInterface) {
		this.customerModelInterface = customerModelInterface;
		activeItemList = new SimpleListProperty<>();
		customerModelInterface.addListener(this);
	}

	public void loadAllProductsToModel() {
		// Load products from Database
		customerModelInterface.updateWares();
	}

	public void addToBasket(int item, int amount) {
		// produktID kan ikke være null
//		if (item == null || item.equals("")) {
//			System.out.println("Item error: " + item);
//		} else
		if (amount <= 0) {
			System.out.println("Invalid amount");
		} else {
			// TODO: Tjek om søgning virker
			for (Product product : activeItemList) {
				if (product.getWareNumber() == item) {
					customerModelInterface.addToBasket(product, amount); // Product typer er forskellige
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

	private void getAllWares()
	{
		activeItemList.set(FXCollections.observableList(customerModelInterface.getAllWares()));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("waresUpdated")) getAllWares();


//		HashMap<String, ArrayList<Product>> waresFromServer = (HashMap<String, ArrayList<Product>>) evt.getNewValue();
//		List<Product> unitWares = new ArrayList<>(waresFromServer.keySet());
//		activeItemList.set(FXCollections.observableList(unitWares));
//		activeItemList.set(FXCollections.observableList((List<Product>) evt.getNewValue()));
	}
}
