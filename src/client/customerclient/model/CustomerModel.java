package client.customerclient.model;

import client.core.LoginManager;
import client.network.Client;
import javafx.util.Pair;
import shared.objects.Basket;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Andreas Young, Andreas Østergaard, Frederik Bergmann.

public class CustomerModel implements CustomerModelInterface {
	private final PropertyChangeSupport support;
	private final Client client;
	private final Basket myBasket;
	private List<Product> wareList;

	public CustomerModel(Client client) {
		support = new PropertyChangeSupport(this);
		this.client = client;
		this.client.addListener(this);
		wareList = new ArrayList<>();
		myBasket = new Basket();
	}

	/**
	 * Adds a product to the Users Basket
	 *
	 * @param product The product of which to add to user basket
	 * @param amount  The amount of the product to be added to the basket
	 */
	public void addToBasket(Product product, int amount) {
		myBasket.addProduct(product, amount);
	}

	/**
	 * Removes all of an item from the Users Basket
	 *
	 * @param product The product which is wished to be removed from the Users Basket
	 */
	public void removeFromBasket(Product product) {
		myBasket.removeProduct(product);
	}

	/**
	 * Changes the amount of a product in the Users Basket
	 *
	 * @param product   The product of which to change the amount
	 * @param newAmount The new desired amount of the specified product
	 */
	public void changeAmount(Product product, int newAmount) {
		myBasket.changeAmount(product, newAmount);
	}

	/**
	 * Requests an updated warelist from the Client
	 */
	public void updateWares() {
		client.getWares();
	}

	/**
	 * Requests the users current basket
	 *
	 * @return The active basket
	 */
	public Basket getMyBasket() {
		return myBasket;
	}

	/**
	 * Requests the client sending an order to the server
	 *
	 * @param basket A Basket consisting of the items desired for the order sent
	 * @param sum    The summed price of all the items in the order
	 * @return True if order was correctly sent, false if any error occured
	 */
	public Pair<Boolean, ArrayList<Product>> sendOrder(Basket basket, double sum) {
		try {
			return client.sendOrder(LoginManager.cvr, basket);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return new Pair<>(false, null);
	}

	/**
	 * Creates a List of products, consisting of the products in the Model's current warelist HashMap
	 *
	 * @return Arraylist of products currently stored on the Model
	 */
	public List<Product> getAllWares() {
		return wareList;
	}


	@Override
	public void emptyBasket() {
		myBasket.empty();
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
		wareList = (List<Product>) evt.getNewValue();
		support.firePropertyChange(
				new PropertyChangeEvent(this, "waresUpdated", null, null));
	}
}
