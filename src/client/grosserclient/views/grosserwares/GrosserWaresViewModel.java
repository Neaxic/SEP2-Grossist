package client.grosserclient.views.grosserwares;

import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.GrosserViewModel;
import javafx.util.Pair;
import shared.network.Subject;
import shared.objects.ProductAndInt;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

// Young

public class GrosserWaresViewModel implements GrosserViewModel, Subject {
	private GrosserModelInterface grosserModel;
	private ArrayList<ProductAndInt> thisIsGettingRatherAnnoying;
	private HashMap<Product, Integer> detailedProductMap;
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

	private void updateViewList() {
		thisIsGettingRatherAnnoying.clear();
		for (Product p : detailedProductMap.keySet()) {
			thisIsGettingRatherAnnoying.add(new ProductAndInt(p.getWareName(), p.getWareNumber(), detailedProductMap.get(p)));
		}
	}

	public void deleteItem(Object o) {
		grosserModel.deleteItem(((ProductAndInt) o).getProductID());
	}

	public void changeAmount(Object o, int newValue) {
		for (Product p : detailedProductMap.keySet()) {
			if(((ProductAndInt) o).getProductID() == p.getWareNumber()){
				detailedProductMap.put(p, newValue);
				grosserModel.changeAmount(new Pair<>(p, detailedProductMap.get(p)));
				break;
			}
		}
		updateViewList();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("grosserProductList")) {
			detailedProductMap = (HashMap<Product, Integer>) evt.getNewValue();
			updateViewList();
			support.firePropertyChange(evt.getPropertyName(), null, null);
		}
	}
}
