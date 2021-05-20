package client.grosserclient.views.grosserwares;

import client.core.factories.ModelFactory;
import shared.objects.ProductAndInt;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.GrosserViewModel;
import shared.network.Subject;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class GrosserWaresViewModel implements GrosserViewModel, Subject {
	private GrosserModelInterface grosserModel;
	private ArrayList<ProductAndInt> thisIsGettingRatherAnnoying;
	private PropertyChangeSupport support;


	public GrosserWaresViewModel() {
		grosserModel = ModelFactory.getInstance().getGrosserModel();
		grosserModel.addListener(this);
		thisIsGettingRatherAnnoying = new ArrayList<>();
		support = new PropertyChangeSupport(this);
	}

	public void updateWareList() {
		thisIsGettingRatherAnnoying.clear();
		grosserModel.requestAllWaresAndAmounts();
	}

	public ArrayList<ProductAndInt> getThisIsGettingRatherAnnoying() {
		return thisIsGettingRatherAnnoying;
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
		if (evt.getPropertyName().equals("grosserProductList")) {
			for (Product p : ((HashMap<Product, Integer>)evt.getNewValue()).keySet()) {
				thisIsGettingRatherAnnoying.add(new ProductAndInt(p.getWareName(), p.getWareNumber(), ((HashMap<Product, Integer>) evt.getNewValue()).get(p)));
			}
			support.firePropertyChange(evt.getPropertyName(), null, null);
		}
	}

	public void deleteItem(Object o) {
		grosserModel.deleteItem(((ProductAndInt)o).getProductID());
	}
}
