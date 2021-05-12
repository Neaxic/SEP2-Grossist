package client.customerclient.model;

import client.core.LoginManager;
import client.network.Client;
import client.network.RMIClient;
import shared.wares.Basket;
import shared.wares.Product;
import shared.wares.OLD_Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

//Andreas Young

public class Model implements CustomerModelInterface {
	private PropertyChangeSupport support;
	RMIClient client;
	Basket myBasket; // Måske fjerne klassen da metodekaldene ikke ændres
	HashMap<String, ArrayList<Product>> wareList; // Lave dette til en klasse hvis vi beholder Basket?

	public Model(Client client) {
		support = new PropertyChangeSupport(this);
		this.client = (RMIClient) client;
		this.client.addListener(this);
		wareList = new HashMap<>();
		myBasket = new Basket();
	}

	public void addToBasket(Product product, int amount) {
		myBasket.addProduct(product, amount);
	}

	public void removeFromBasket(Product product) {
		myBasket.removeProduct(product);
	}

	public void changeAmount(Product product, int newAmount) {
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
		wareList = (HashMap<String, ArrayList<Product>>) evt.getNewValue();
		support.firePropertyChange(new PropertyChangeEvent(this, "waresUpdated", null, null));
	}

	public void updateWares() {
		client.getWares();
	}

	public Basket getMyBasket() {
		return myBasket;
	}

	//TODO: KOM TILBAGE HER TIL NOT DONE MAKKER
	public void sendOrder(Basket basket, double sum){
		client.sendOrder(LoginManager.cvr, basket, sum);
	}

	public ArrayList<Product> getAllWares()
	{
		ArrayList<Product> returnList = new ArrayList<>();

		for (ArrayList<Product> list : wareList.values())
		{
			returnList.addAll(list);
		}

		return returnList;
	}

	public ArrayList<Product> getCategory(String category)
	{
		return wareList.get(category);
	}
}
