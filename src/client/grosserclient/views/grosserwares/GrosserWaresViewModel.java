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
import java.util.List;

// Andreas Young.

public class GrosserWaresViewModel implements GrosserViewModel, Subject {
	private GrosserModelInterface grosserModel;
	private List<ProductAndInt> listForView;
	private List<Pair<Product, Integer>> detailedProductMap;
	private PropertyChangeSupport support;


	public GrosserWaresViewModel() {
		grosserModel = ModelFactory.getInstance().getGrosserModel();
		grosserModel.addListener(this);
		detailedProductMap = new ArrayList<>();
		listForView = new ArrayList<>();
		support = new PropertyChangeSupport(this);
	}

	public void updateWareList() {
		detailedProductMap = new ArrayList<>();
		listForView = new ArrayList<>();
		grosserModel.requestAllWaresAndAmounts();
	}

	public List<ProductAndInt> getListForView() {
		return listForView;
	}

	private void updateViewList() {
		for (Pair<Product, Integer> p : detailedProductMap) {
			listForView.add(new ProductAndInt(p.getKey().getWareName(), p.getKey().getWareNumber(), p.getValue()));
		}
	}

	public void deleteItem(Object o) {
		for (Pair<Product, Integer> p : detailedProductMap) {
			if (p.getKey().getWareNumber() == ((ProductAndInt) o).getProductID()) {
				grosserModel.deleteItem(p.getKey());
			}
		}
	}

	public void increaseStock(Object o, int amountChange) {
		for (Pair<Product, Integer> p : detailedProductMap) {
			if (p.getKey().getWareNumber() == ((ProductAndInt) o).getProductID()) {
				Pair<Product, Integer> newPair = new Pair<>(p.getKey(), amountChange);
				detailedProductMap.set(detailedProductMap.indexOf(p), newPair);
				grosserModel.increaseStock(newPair);
				break;
			}
		}
	}

	public void reduceStock(Object o, int amountChange) {
		for (Pair<Product, Integer> p : detailedProductMap) {
			if (p.getKey().getWareNumber() == ((ProductAndInt) o).getProductID() && ((ProductAndInt) o).getAmount()-amountChange > 0) {
				Pair<Product, Integer> newPair = new Pair<>(p.getKey(), amountChange);
				detailedProductMap.set(detailedProductMap.indexOf(p), newPair);
				grosserModel.reduceStock(newPair);
				break;
			}
		}
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
			detailedProductMap = (List<Pair<Product, Integer>>) evt.getNewValue();
			updateViewList();
			support.firePropertyChange(evt.getPropertyName(), null, null);
		}
	}
}
