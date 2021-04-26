package client.customerclient.model;

import client.network.Client;
import client.network.RMIClient;
import shared.wares.Basket;
import shared.wares.NewProduct;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class Model implements CustomerModelInterface {
	private PropertyChangeSupport support;
	RMIClient client;
	Basket myBasket; // Måske fjerne klassen da metodekaldene ikke ændres
	HashMap<Product, Integer> wareList; // Lave dette til en klasse hvis vi beholder Basket?

	public Model(Client client) {
		support = new PropertyChangeSupport(this);
		this.client = (RMIClient) client;
		this.client.addListener(this);
		wareList = new HashMap<>();
		myBasket = new Basket();
	}

	void addToBasket(NewProduct product, int amount) {
		myBasket.addProduct(product, amount);
	}

	void removeFromBasket(NewProduct product) {
		myBasket.removeProduct(product);
	}

	void changeAmount(NewProduct product, int newAmount) {
		myBasket.changeAmount(product, newAmount);
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void removeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) { // CustomerBrowserViewModel is a listener
		wareList = (HashMap<Product, Integer>) evt.getNewValue();
		support.firePropertyChange(evt);
	}

	public void getAllWares() {
		client.getWares();
	}

}
