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
import java.lang.reflect.Array;
import java.util.ArrayList;

// Andreas Young, Line Guld, Andreas Østergaard

public class CustomerBrowserViewModel implements CustomerViewModel, PropertyChangeListener {
	private final CustomerModelInterface customerModelInterface;

	//Burde måske renames til alle items
	private SimpleListProperty<Product> activeItemList;

	private ArrayList<Product> SpecificItemList;

	public CustomerBrowserViewModel() {
		this.customerModelInterface = ModelFactory.getInstance().getCustomerModel();
		activeItemList = new SimpleListProperty<>();
		customerModelInterface.addListener(this);
		SpecificItemList = new ArrayList<>();
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

	public ArrayList<Product> populate(String category){
		SpecificItemList.clear();
		switch (category) {
			case "Alt" -> {
				for (Product item : activeItemList) {
					SpecificItemList.add(item);
				}
				return SpecificItemList;
			}
			case "Drikkevarer (Med alkohol)" -> {
				for (Product item : activeItemList) {
					if (item.getClass().getSimpleName().contentEquals("Alcohol")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}

			case "Drikkevarer (Uden alkohol)" -> {
				for (Product item : activeItemList) {
					if (item.getClass().getSimpleName().contentEquals("Drink")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Kød og fisk" -> {
				for (Product item : activeItemList) {
					if (item.getClass().getSimpleName().contentEquals("MeatAndFish")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Frugt og grønt" -> {
				for (Product item : activeItemList) {
					if (item.getClass().getSimpleName().contentEquals("FruitsAndVegetable")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Frostvarer" -> {
				for (Product item : activeItemList) {
					if (item.getClass().getSimpleName().contentEquals("Frozen")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Mejeri og Æg" -> {
				for (Product item : activeItemList) {
					if (item.getClass().getSimpleName().contentEquals("CooledAndDairy")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Kolonial" -> {
				for (Product item : activeItemList) {
					if (item.getClass().getSimpleName().contentEquals("Colonial")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}

			// TAGS

			case "Øko" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Øko")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Nøgle" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Nøgle")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "GMO" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("GMO")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Laktosefri" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Laktosefri")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Vegansk" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Vegansk")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Vegetar" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Vegetar")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Fedtfattig" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Fedtfattig")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Halal" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Halal")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "MSE" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("MSE")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Sukkerfri" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Sukkerfri")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Glutenfri" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Glutenfri")) {
						SpecificItemList.add(item);
					}
				}
			}
			case "Stråforkortere" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Stråforkortere")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
			case "Alkoholfri" -> {
				for (Product item : activeItemList) {
					if (item.getTags().contains("Alkoholfri")) {
						SpecificItemList.add(item);
					}
				}
				return SpecificItemList;
			}
		}
		return SpecificItemList;
	}

	public ArrayList<Product> searchBtn(String input){
		SpecificItemList.clear();
		System.out.println(input);
		for(Product item : activeItemList){
			if(item.getWareName().contains(input)){
				SpecificItemList.add(item);
			}
		}
		return SpecificItemList;
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
