package client.grosserclient.model;

import client.network.GrosserClient;
import javafx.util.Pair;
import shared.objects.CustomerContainer;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class GrosserModel implements GrosserModelInterface {
	private PropertyChangeSupport support;
	private final GrosserClient client;

	public GrosserModel(GrosserClient client) {
		support = new PropertyChangeSupport(this);
		this.client = client;
		this.client.addListener(this);
	}

	@Override
	public void getAllOrders() {
		client.getAllOrders();
	}

	@Override
	public void requestAllWaresAndAmounts() {
		client.requestGrosserProducts();
	}

	@Override
	public void deleteItem(Product product) {
		client.deleteWare(product);
	}

	@Override
	public void increaseStock(Pair<Product, Integer> productAndAmountToIncrease) {
		client.increaseStock(productAndAmountToIncrease);
	}

	@Override
	public void reduceStock(Pair<Product, Integer> productAndAmountToReduce) {
		client.reduceStock(productAndAmountToReduce);
	}

	@Override
	public boolean addCustomer(CustomerContainer customer) {
		return client.addCustomer(customer);
	}

	@Override public void getRiskData()
	{
		client.getRiskData();
	}

	@Override
	public void createNewProduct(Pair<Product, Integer> newProduct) throws IllegalArgumentException, SQLException {
		client.createProduct(newProduct);
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
	public void propertyChange(PropertyChangeEvent evt) {
		support.firePropertyChange(evt);
	}
}
