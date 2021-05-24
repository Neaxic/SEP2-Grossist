package client.customerclient.views.customerbrowser;

import client.core.factories.ModelFactory;
import client.customerclient.model.CustomerModelInterface;
import client.customerclient.views.CustomerViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Andreas Young, Line Guld

public class CustomerBrowserViewModel implements CustomerViewModel, PropertyChangeListener {
	private final CustomerModelInterface customerModelInterface;
	private SimpleListProperty<Product> activeItemList;

	public CustomerBrowserViewModel() {
		this.customerModelInterface = ModelFactory.getInstance().getCustomerModel();
		activeItemList = new SimpleListProperty<>();
		customerModelInterface.addListener(this);
	}

	public void loadAllProductsToModel() {
		customerModelInterface.updateWares();
	}

	public void addToBasket(int productWareNumber, int amount) {
		if (amount <= 0) {
			new Alert(Alert.AlertType.ERROR, "Mængde skal være et Positivt Heltal", ButtonType.OK).showAndWait();
		} else {
			// TODO: Tjek om søgning virker
			for (Product product : activeItemList) {
				if (product.getWareNumber() == productWareNumber) {
					customerModelInterface.addToBasket(product, amount);
				}
			}
		}
	}

	public SimpleListProperty<Product> activeItemListProperty() {
		return activeItemList;
	}

	private void getAllWares() {
		activeItemList.set(FXCollections.observableList(customerModelInterface.getAllWares()));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("waresUpdated")) {
			getAllWares();
		}
	}
}
