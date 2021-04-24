package client.customerclient.model;

import client.network.Client;
import client.network.RMIClient;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Model implements CustomerModelInterface {
	private PropertyChangeSupport support;
	RMIClient client;

	List<Product> wareList;

	public Model(Client client) {
		support = new PropertyChangeSupport(this);
		this.client = (RMIClient) client;
		this.client.addListener(this);
		wareList = new ArrayList<>();
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
		support.firePropertyChange(evt);
	}

	public void getAllWares() {
		client.getWares();
	}

//	public Product getWare(String name){
//		wareList.get(wareList.indexOf())
//	}
}
